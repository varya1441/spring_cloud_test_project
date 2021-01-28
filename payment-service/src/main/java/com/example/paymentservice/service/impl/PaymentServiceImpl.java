package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.Status;
import com.example.paymentservice.exception.ItemNotFoundException;
import com.example.paymentservice.mapper.PaymentMapper;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment findByPaymentId(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class + "with id " + id));
    }


    @Override
    public PaymentDTO calculatePayment(PaymentDTO paymentDTO, UUID employeeId) {
        Payment payment = paymentMapper.mapToEntity(paymentDTO);
        payment.setWages(payment.getRate() * payment.getWorkingHours());
        payment.setEmployeeId(employeeId);

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.mapToDTO(savedPayment);
    }

    @Override
    public void changePaymentStatus(UUID employeeId, OffsetDateTime effectiveDate) {
        Payment p = findByEmployeeId(employeeId).stream().filter(payment ->
                checkDate(effectiveDate, payment)).findFirst().orElseThrow(() ->
                new ItemNotFoundException("couldn't find employee" + employeeId + " for effective date " + effectiveDate));

        Status status = p.getStatus().equals(Status.PAID) ? Status.UNPAID : Status.PAID;
        p.setStatus(status);
    }

    private boolean checkDate(OffsetDateTime effectiveDate, Payment payment) {
        boolean isInPeriod = payment.getFromDate().isBefore(effectiveDate) && payment.getToDate().isAfter(effectiveDate);
        boolean isInBounds = payment.getFromDate().isEqual(effectiveDate) || payment.getToDate().isEqual(effectiveDate);
        return isInPeriod || isInBounds;
    }

    public List<Payment> findByEmployeeId(UUID employeeId) {
        List<Payment> payments = paymentRepository.findAllByEmployeeId(employeeId);

        if (payments == null || payments.isEmpty()) {
            throw new EntityNotFoundException(Payment.class + "with employeeId " + employeeId);
        }

        return payments;
    }

}
