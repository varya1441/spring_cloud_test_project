package com.example.userservice.controller;

import com.example.userservice.dto.CompanyDTO;
import com.example.userservice.exception.RestExceptionHandler;
import com.example.userservice.exception.ValidationException;
import com.example.userservice.service.CompanyService;
import com.example.userservice.validators.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private CompanyService companyService;
    private CompanyValidator companyValidator;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyValidator companyValidator) {
        this.companyService = companyService;
        this.companyValidator = companyValidator;
    }

    @PostMapping("/employee/company")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO, BindingResult bindingResult) {
        companyValidator.validate(companyDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        return new ResponseEntity<>(companyService.saveCompany(companyDTO), HttpStatus.OK);
    }
}
