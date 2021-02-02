package com.example.paymentservice.service.impl;

import com.example.paymentservice.controller.EmployeeServiceFeignClient;
import com.example.paymentservice.dto.CompensationPaymentDTO;
import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.dto.PaymentEmployeeInfo;
import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.PaymentMethod;
import com.example.paymentservice.entity.Status;
import com.example.paymentservice.mapper.PaymentEmployeeMapper;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final Converter converter;
    private final EmployeeServiceFeignClient employeeServiceFeignClient;
    private final PaymentEmployeeMapper paymentEmployeeMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, Converter converter, EmployeeServiceFeignClient employeeServiceFeignClient, PaymentEmployeeMapper paymentEmployeeMapper) {
        this.paymentRepository = paymentRepository;
        this.converter = converter;
        this.employeeServiceFeignClient = employeeServiceFeignClient;
        this.paymentEmployeeMapper = paymentEmployeeMapper;
    }

    @Override
    public Payment findByPaymentId(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(Payment.class + "with id " + id + " not found");
                    return new EntityNotFoundException(Payment.class + "with id " + id);
                });
    }

    @Override
    @Transactional
    public List<Payment> changePaymentStatus(UUID employeeId, PaymentRequest paymentRequest) {
        OffsetDateTime fromDate = converter.convertToOffset(paymentRequest.getFromDate());
        OffsetDateTime toDate = converter.convertToOffset(paymentRequest.getToDate());

        List<Payment> payments = paymentRepository.getEmployeePaymentsForPeriod(fromDate, toDate, employeeId);
        log.info("Payments amount" + payments.size());
        for (Payment payment : payments) {
            payment.setStatus(payment.getStatus().equals(Status.PAID) ? Status.UNPAID : Status.PAID);
        }
        return payments;
    }

    @Override
    public List<PaymentEmployeeInfo> findByEmployeeId(UUID employeeId) {
        List<Payment> payments = paymentRepository.findAllByEmployeeId(employeeId);

        if (CollectionUtils.isEmpty(payments)) {
            log.error("payments is empty or not found for employee id" + employeeId);
            throw new EntityNotFoundException(Payment.class + " with employeeId " + employeeId + "not found");
        } else {
            return payments.stream().map(paymentEmployeeMapper::mapToDTO).collect(Collectors.toList());
        }

    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Payment payment = findByPaymentId(id);
        payment.setDeleted(true);
        log.info("payment with id" + id + " deleted");
    }

    @Override
    public List<Payment> getPayments(PaymentRequest paymentRequest) {
        log.info("sent to employee service");
        PaymentDTO paymentsDTOs = employeeServiceFeignClient.getPayments(paymentRequest);
        log.info("payments: " + paymentsDTOs);

        OffsetDateTime fromDate = paymentsDTOs.getFromDate();
        OffsetDateTime toDate = paymentsDTOs.getToDate();

        List<Payment> payments = new ArrayList<>();
        for (Map.Entry<UUID, List<CompensationPaymentDTO>> paymentEntry : paymentsDTOs.getPayments().entrySet()) {
            CompensationPaymentDTO beforeCompensationPayment = null;
            List<CompensationPaymentDTO> compensationPaymentDTOS = paymentEntry.getValue();
            compensationPaymentDTOS.sort(Comparator.comparing(CompensationPaymentDTO::getEffectiveDate));

            for (CompensationPaymentDTO compensationPayment : compensationPaymentDTOS) {
                OffsetDateTime effectiveDate = compensationPayment.getEffectiveDate();

                if (effectiveDate.isBefore(fromDate) || effectiveDate.isEqual(fromDate)) {
                    beforeCompensationPayment = compensationPayment;
                } else if ((effectiveDate.isAfter(fromDate) || effectiveDate.isEqual(fromDate)) && (effectiveDate.isBefore(toDate) || effectiveDate.isEqual(toDate)) && beforeCompensationPayment != null) {
                    getCompensationPayment(fromDate, effectiveDate, payments, paymentEntry, beforeCompensationPayment);
                    fromDate = effectiveDate;
                    beforeCompensationPayment = compensationPayment;
                } else if (beforeCompensationPayment != null) {
                    getCompensationPayment(beforeCompensationPayment.getEffectiveDate(), toDate, payments, paymentEntry, beforeCompensationPayment);
                } else {
                    beforeCompensationPayment = compensationPayment;
                    getCompensationPayment(effectiveDate, toDate, payments, paymentEntry, beforeCompensationPayment);
                }
            }
        }

        return payments;
    }

    @Transactional
    public void getCompensationPayment(OffsetDateTime fromDate, OffsetDateTime toDate, List<Payment> payments, Map.Entry<UUID, List<CompensationPaymentDTO>> paymentEntry, CompensationPaymentDTO beforeCompensationPayment) {
        long days = DAYS.between(fromDate, toDate);
        double rate = beforeCompensationPayment.getSalaryPerHour();
        double wages = days * rate;
        Payment payment = new Payment(wages, PaymentMethod.INVOICE, Status.UNPAID, fromDate, toDate, (int) days, rate, paymentEntry.getKey());

        paymentRepository.save(payment);
        log.info("payment is saved" + payment);
        payments.add(payment);
    }

}
