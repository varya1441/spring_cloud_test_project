package com.example.userservice.controller;

import com.example.userservice.dto.DepartmentDTO;
import com.example.userservice.exception.RestExceptionHandler;
import com.example.userservice.exception.ValidationException;
import com.example.userservice.service.DepartmentService;
import com.example.userservice.validators.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    private DepartmentService departmentService;
    private DepartmentValidator departmentValidator;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentValidator departmentValidator) {
        this.departmentService = departmentService;
        this.departmentValidator = departmentValidator;
    }

    @PostMapping("/department")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DepartmentDTO> saveUserDepartment(@RequestBody DepartmentDTO departmentDTO, BindingResult bindingResult) {
        departmentValidator.validate(departmentDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return new ResponseEntity<>(departmentService.saveDepartment(departmentDTO), HttpStatus.OK);
    }
}
