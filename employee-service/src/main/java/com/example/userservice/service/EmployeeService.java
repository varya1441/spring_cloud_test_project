package com.example.userservice.service;

import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    Employee findEmployeeById(UUID id);

    void delete(UUID id);

    EmployeeDTO findByLogin(String login);
}
