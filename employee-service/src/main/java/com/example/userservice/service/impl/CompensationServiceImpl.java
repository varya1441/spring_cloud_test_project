package com.example.userservice.service.impl;

import com.example.userservice.dto.CompensationDTO;
import com.example.userservice.dto.payment.CompensationPaymentDTO;
import com.example.userservice.dto.payment.PaymentDTO;
import com.example.userservice.entity.Compensation;
import com.example.userservice.entity.Employee;
import com.example.userservice.mapper.CompensationMapper;
import com.example.userservice.mapper.CompensationPaymentMapper;
import com.example.userservice.repository.CompensationFiltration;
import com.example.userservice.repository.CompensationRepository;
import com.example.userservice.service.CompensationService;
import com.example.userservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    private static final int HOURS_IN_WEEK = 40;
    private static final int HOURS_IN_MONTH = 160;


    public CompensationServiceImpl(CompensationRepository compensationRepository, CompensationMapper compensationMapper, CompensationPaymentMapper compensationPaymentMapper, EmployeeService employeeService) {
        this.compensationRepository = compensationRepository;
        this.compensationMapper = compensationMapper;
        this.compensationPaymentMapper = compensationPaymentMapper;
        this.employeeService = employeeService;
    }

    @Override
    public Compensation findCompensationById(UUID id) {
        return compensationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Compensation.class + "with id " + id));
    }

    @Override
    public PaymentDTO getPaymentInfo( OffsetDateTime fromDate, OffsetDateTime toDate) {
        PaymentDTO paymentDTO = new PaymentDTO(fromDate, toDate);

        List<Compensation> compensations = compensationRepository.findAll(Specification.where(CompensationFiltration.isFromTo(fromDate, toDate)));
        compensations.addAll(compensationRepository.findAll(Specification.where(CompensationFiltration.isFrom(fromDate))));
        compensations.forEach(compensation ->
                createPayment(compensation,paymentDTO));
        return paymentDTO;
    }

    private void createPayment(Compensation compensation, PaymentDTO paymentDTO) {
        UUID employeeId = compensation.getEmployee().getId();
        CompensationPaymentDTO compensationPaymentDTO = compensationPaymentMapper.mapToDTO(compensation);
        if (paymentDTO.getPayments().containsKey(employeeId)) {
            paymentDTO.getPayments().get(employeeId).add(compensationPaymentDTO);
        } else {
            paymentDTO.getPayments().put(employeeId, new ArrayList<>(Arrays.asList(compensationPaymentDTO)));
        }
    }

    @Override
    public CompensationDTO saveCompensation(CompensationDTO compensationDTO) {
        Compensation compensation = compensationMapper.mapToEntity(compensationDTO);

        Double salaryPerHour = compensation.getSalaryPerHour();
        compensation.setSalaryPerWeek(salaryPerHour * HOURS_IN_WEEK);
        compensation.setSalaryPerMonth(salaryPerHour * HOURS_IN_MONTH);
        Employee employee = employeeService.findEmployeeById(compensationDTO.getEmployeeId());
        compensation.setEmployee(employee);
        Compensation savedCompensation = compensationRepository.save(compensation);
        log.info("Compensation is saved");

        return compensationMapper.mapToDTO(savedCompensation);
    }
}
