package com.example.userservice.controller;

import com.example.userservice.dto.CompensationDTO;
import com.example.userservice.exception.RestExceptionHandler;
import com.example.userservice.exception.ValidationException;
import com.example.userservice.service.CompensationService;
import com.example.userservice.validators.CompensationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private CompensationService compensationService;
    private CompensationValidator compensationValidator;

    public CompensationController(CompensationService compensationService, CompensationValidator compensationValidator) {
        this.compensationService = compensationService;
        this.compensationValidator = compensationValidator;
    }

    @PostMapping("/compensation")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CompensationDTO> saveEmployeeCompensation(@RequestBody CompensationDTO compensationDTO, BindingResult bindingResult) {
        compensationValidator.validate(compensationDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return new ResponseEntity<>(compensationService.saveCompensation(compensationDTO), HttpStatus.OK);
    }

//    @GetMapping("/compensation/{id}")
//    public ResponseEntity<PaymentDTO> getEmployeeCompensation(@PathVariable UUID id) {
//        return new ResponseEntity<>(compensationService.getPaymentInfo(id), HttpStatus.OK);
//    }
}