package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentEmployeeInfo;
import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.entity.Payment;

import java.util.List;
import java.util.UUID;


public interface PaymentService {
    Payment findByPaymentId(UUID id);

    List<Payment> getPayments(PaymentRequest paymentRequest);

    List<PaymentEmployeeInfo> findByEmployeeId(UUID employeeId);

    void delete(UUID id);

    List<Payment> changePaymentStatus(UUID employeeId, PaymentRequest paymentRequest);
}
