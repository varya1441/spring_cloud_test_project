package com.example.paymentservice.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PaymentRequest {
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
}
