package com.example.userservice.dto.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class CompensationPaymentDTO {
    private OffsetDateTime effectiveDate;
    private Double salaryPerHour;
}
