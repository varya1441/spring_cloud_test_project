package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class CompensationDTO {
    private UUID id;
    private OffsetDateTime effectiveDate;
    private Double salaryPerHour;//rate
    private Double salaryPerWeek;
    private Double salaryPerMonth;
    @NotNull
    private UUID employeeId;
}
