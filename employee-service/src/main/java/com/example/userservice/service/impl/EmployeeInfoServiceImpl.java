package com.example.userservice.service.impl;

import com.example.userservice.controller.EmployeeServiceFeignClient;
import com.example.userservice.dto.EmployeeInfo;
import com.example.userservice.dto.payment.Payment;
import com.example.userservice.entity.Employee;
import com.example.userservice.mapper.EmployeeInfoMapper;
import com.example.userservice.service.AddressService;
import com.example.userservice.service.EmployeeInfoService;
import com.example.userservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    private final EmployeeInfoMapper employeeInfoMapper;
    private final EmployeeService employeeService;
    private final EmployeeServiceFeignClient employeeServiceFeignClient;

    public EmployeeInfoServiceImpl(EmployeeInfoMapper employeeInfoMapper, EmployeeService employeeService, EmployeeServiceFeignClient employeeServiceFeignClient, AddressService addressService) {
        this.employeeInfoMapper = employeeInfoMapper;
        this.employeeService = employeeService;
        this.employeeServiceFeignClient = employeeServiceFeignClient;
    }

    @Override
    @Transactional
    public EmployeeInfo getEmployeeInfo(UUID employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        EmployeeInfo employeeInfo = employeeInfoMapper.mapToDTO(employee);

        log.info("send request to employee service");
        List<Payment> payments = employeeServiceFeignClient.getPayment(employeeId);
        log.info("Got payments");

        employeeInfo.setPayments(payments);
        return employeeInfo;
    }
}
