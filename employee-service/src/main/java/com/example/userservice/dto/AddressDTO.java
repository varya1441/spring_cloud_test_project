package com.example.userservice.dto;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AddressDTO {
    private UUID id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String state;
    private String city;
    private OffsetDateTime movingDate;
    private OffsetDateTime leavingDate;
    private String zipCode;
    @NotNull(message = "employee not found")
    private UUID employeeId;
    @NotNull(message = "company not found")
    private UUID companyId;
}
