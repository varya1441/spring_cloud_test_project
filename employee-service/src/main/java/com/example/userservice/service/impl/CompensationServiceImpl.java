package com.example.userservice.service.impl;

import com.example.userservice.dto.CompensationDTO;
import com.example.userservice.dto.payment.CompensationPaymentDTO;
import com.example.userservice.dto.payment.PaymentDTO;
import com.example.userservice.dto.payment.PaymentRequest;
import com.example.userservice.entity.Compensation;
import com.example.userservice.entity.Employee;
import com.example.userservice.mapper.CompensationMapper;
import com.example.userservice.mapper.CompensationPaymentMapper;
import com.example.userservice.repository.CompensationRepository;
import com.example.userservice.service.CompensationService;
import com.example.userservice.service.EmployeeService;
import com.example.userservice.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CompensationServiceImpl implements CompensationService {
    private final CompensationRepository compensationRepository;
    private final CompensationMapper compensationMapper;
    private final CompensationPaymentMapper compensationPaymentMapper;
    private final EmployeeService employeeService;
    private final Converter converter;

    private static final int HOURS_IN_WEEK = 40;
    private static final int HOURS_IN_MONTH = 160;


    public CompensationServiceImpl(CompensationRepository compensationRepository, CompensationMapper compensationMapper, CompensationPaymentMapper compensationPaymentMapper, EmployeeService employeeService, Converter converter) {
        this.compensationRepository = compensationRepository;
        this.compensationMapper = compensationMapper;
        this.compensationPaymentMapper = compensationPaymentMapper;
        this.employeeService = employeeService;
        this.converter = converter;
    }

    @Override
    @Transactional
    public Compensation findCompensationById(UUID id) {
        return compensationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(Compensation.class + "with id " + id + " not found");
                    return new EntityNotFoundException(Compensation.class + "with id " + id);
                });
    }

    @Override
    @Transactional
    public PaymentDTO getPaymentInfo(PaymentRequest paymentRequest) {
        OffsetDateTime fromDate =converter.convertToOffset(paymentRequest.getFromDate());
        OffsetDateTime toDate = converter.convertToOffset(paymentRequest.getToDate());
        PaymentDTO paymentDTO = new PaymentDTO(fromDate, toDate);

        List<Employee> employees = employeeService.getAllEmployee();

        for (Employee employee : employees) {
            List<Compensation> compensationList = compensationRepository.getAllEmployeeCompensationsBetweenDates(fromDate, toDate, employee.getId());
            for (Compensation compensation : compensationList) {
                createPayment(employee.getId(), compensation, paymentDTO);
            }
        }

        log.info("paymentDtos: " + paymentDTO);
        return paymentDTO;
    }

    private void createPayment(UUID employeeId, Compensation compensation, PaymentDTO paymentDTO) {
        CompensationPaymentDTO compensationPaymentDTO = compensationPaymentMapper.mapToDTO(compensation);
        if (paymentDTO.getPayments().containsKey(employeeId)) {
            paymentDTO.getPayments().get(employeeId).add(compensationPaymentDTO);
        } else {
            paymentDTO.getPayments().put(employeeId, new ArrayList<>(Arrays.asList(compensationPaymentDTO)));
        }
    }

    @Override
    @Transactional
    public CompensationDTO saveCompensation(CompensationDTO compensationDTO) {
        Compensation compensation = compensationMapper.mapToEntity(compensationDTO);
        Employee employee = employeeService.findEmployeeById(compensationDTO.getEmployeeId());

        Double salaryPerHour = compensation.getSalaryPerHour();
        compensation.setSalaryPerWeek(salaryPerHour * HOURS_IN_WEEK);
        compensation.setSalaryPerMonth(salaryPerHour * HOURS_IN_MONTH);
        compensation.setEmployee(employee);

        Compensation savedCompensation = compensationRepository.save(compensation);
        log.info("Compensation is saved");

        return compensationMapper.mapToDTO(savedCompensation);
    }
}
