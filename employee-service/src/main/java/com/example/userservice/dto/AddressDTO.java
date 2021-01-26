package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Data
public class AddressDTO {

    private String id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String state;
    private String city;
    private Timestamp movingDate;
    private Timestamp leavingDate;
    private String zipCode;
    @NotNull
    private String employeeId;
    @NotNull
    private String companyId;
}
