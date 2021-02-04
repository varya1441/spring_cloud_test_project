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
    private UUID employeeId;
    private UUID companyId;

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", movingDate=" + movingDate +
                ", leavingDate=" + leavingDate +
                ", zipCode='" + zipCode + '\'' +
                ", employeeId=" + employeeId +
                ", companyId=" + companyId +
                '}';
    }
}
