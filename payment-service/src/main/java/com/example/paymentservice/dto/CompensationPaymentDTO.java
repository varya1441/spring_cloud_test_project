package com.example.paymentservice.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CompensationPaymentDTO {
    private OffsetDateTime effectiveDate;
    private Double salaryPerHour;
}
