package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.entity.Payment;

import java.time.OffsetDateTime;
import java.util.UUID;


public interface PaymentService {
    Payment findByPaymentId(UUID id);

    PaymentDTO calculatePayment(PaymentDTO paymentDTO, UUID employeeId);

    void changePaymentStatus(UUID employeeId, OffsetDateTime effectiveDate);
}
