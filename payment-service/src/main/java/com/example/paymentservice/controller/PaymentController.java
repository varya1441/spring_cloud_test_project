package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentEmployeeInfo;
import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.exception.RestExceptionHandler;
import com.example.paymentservice.exception.ValidationException;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.validator.PaymentRequestValidator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController

public class PaymentController {
    private PaymentRequestValidator paymentRequestValidator;
    private PaymentService paymentService;

    public PaymentController(PaymentRequestValidator paymentRequestValidator, PaymentService paymentService) {
        this.paymentRequestValidator = paymentRequestValidator;
        this.paymentService = paymentService;
    }


    @PostMapping("/payments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Payment>> getPayments(@RequestBody @Valid PaymentRequest paymentRequest, BindingResult bindingResult) {
        paymentRequestValidator.validate(paymentRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return ResponseEntity.ok(paymentService.getPayments(paymentRequest));
    }

    @PutMapping(value = "/payments/change-status/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Payment>> getPaymentsU(@PathVariable UUID employeeId, @RequestBody @Valid PaymentRequest paymentRequest, BindingResult bindingResult) {
        paymentRequestValidator.validate(paymentRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return new ResponseEntity<>(paymentService.changePaymentStatus(employeeId, paymentRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/payments/{employeeId}")
    public ResponseEntity<List<PaymentEmployeeInfo>> getPaymentsByEmployeeId(@PathVariable UUID employeeId) {
        return new ResponseEntity<>(paymentService.findByEmployeeId(employeeId), HttpStatus.OK);
    }
    @DeleteMapping(value = "/payments/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
