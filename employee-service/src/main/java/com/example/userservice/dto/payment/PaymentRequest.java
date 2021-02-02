package com.example.userservice.dto.payment;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class PaymentRequest {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
