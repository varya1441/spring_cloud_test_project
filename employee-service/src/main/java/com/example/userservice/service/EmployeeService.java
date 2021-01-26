package com.example.userservice.service;

import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    Employee findEmployeeById(String id);

    EmployeeDTO findByLogin(String login);
}
