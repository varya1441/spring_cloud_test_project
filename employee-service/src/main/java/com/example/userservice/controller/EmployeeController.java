package com.example.userservice.controller;

import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.dto.EmployeeInfo;
import com.example.userservice.entity.Employee;
import com.example.userservice.exception.RestExceptionHandler;
import com.example.userservice.exception.ValidationException;
import com.example.userservice.service.EmployeeInfoService;
import com.example.userservice.service.EmployeeService;
import com.example.userservice.validators.EmployeeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {

    private EmployeeInfoService employeeInfoService;
    private EmployeeService employeeService;
    private EmployeeValidator employeeValidator;


    public EmployeeController(EmployeeInfoService employeeInfoService, EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.employeeInfoService = employeeInfoService;
        this.employeeService = employeeService;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<EmployeeDTO> getByLogin(@PathVariable String login) {
        return new ResponseEntity<>(employeeService.findByLogin(login), HttpStatus.OK);
    }

    @PostMapping("/employee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        employeeValidator.validate(employeeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/employee/info/{employeeId}")
    public ResponseEntity<EmployeeInfo> getEmployeeInfo(@PathVariable UUID employeeId) {
        return new ResponseEntity<>(employeeInfoService.getEmployeeInfo(employeeId), HttpStatus.OK);
    }


}
