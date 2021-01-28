package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.exception.RestExceptionHandler;
import com.example.paymentservice.exception.ValidationException;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.validator.PaymentValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class PaymentController {
    private PaymentValidator paymentValidator;
    private PaymentService paymentService;

    public PaymentController(PaymentValidator paymentValidator, PaymentService paymentService) {
        this.paymentValidator = paymentValidator;
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/{employeeId}")
    public ResponseEntity<PaymentDTO> payments(@RequestBody @Valid PaymentDTO paymentDTO, @PathVariable UUID employeeId, BindingResult bindingResult) {
        paymentValidator.validate(paymentDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return ResponseEntity.ok(paymentService.calculatePayment(paymentDTO, employeeId));
    }
}
