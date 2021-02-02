package com.example.userservice.service;

import com.example.userservice.dto.CompensationDTO;
import com.example.userservice.dto.payment.PaymentDTO;
import com.example.userservice.dto.payment.PaymentRequest;
import com.example.userservice.entity.Compensation;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface CompensationService {
    Compensation findCompensationById(UUID id);

    CompensationDTO saveCompensation(CompensationDTO compensationDTO);

    PaymentDTO getPaymentInfo(PaymentRequest paymentRequest);
}
