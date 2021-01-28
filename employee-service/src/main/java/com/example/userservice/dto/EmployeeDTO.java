package com.example.userservice.dto;

import com.example.userservice.entity.Gender;
import com.example.userservice.entity.Status;
import com.example.userservice.validators.ValidUuid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class EmployeeDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String login;
    private Gender gender;
    private Status status;
    private OffsetDateTime hiringDate;
    private OffsetDateTime terminationDate;
    @ValidUuid
    private UUID departmentId;
    @ValidUuid
    private UUID companyId;

}
