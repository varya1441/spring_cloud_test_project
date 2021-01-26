package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class CompensationDTO {
    private String id;
    private Timestamp effectiveDate;
    private Double salaryPerHour;
    private Double salaryPerWeek;
    private Double salaryPerMonth;
    @NotNull
    private String employeeId;
}
