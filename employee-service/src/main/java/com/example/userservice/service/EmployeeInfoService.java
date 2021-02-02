package com.example.userservice.service;

import com.example.userservice.dto.EmployeeInfo;

import java.util.UUID;

public interface EmployeeInfoService {
    EmployeeInfo getEmployeeInfo(UUID employeeId);
}
