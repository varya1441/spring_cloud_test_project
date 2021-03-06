package com.example.paymentservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
