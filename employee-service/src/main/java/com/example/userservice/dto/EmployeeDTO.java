package com.example.userservice.dto;

import com.example.userservice.entity.Gender;
import com.example.userservice.entity.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Status status;
    private OffsetDateTime hiringDate;
    private OffsetDateTime terminationDate;
    @NotNull
    private String departmentId;
    @NotNull
    private String companyId;

}
