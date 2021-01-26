package com.example.authserver.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Status status;
    private OffsetDateTime hiringDate;
    private OffsetDateTime terminationDate;
    private String departmentId;
    private String companyId;

}
