package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DepartmentDTO{
    private UUID id;
    private String name;
    private String description;
    private UUID companyId;
}
