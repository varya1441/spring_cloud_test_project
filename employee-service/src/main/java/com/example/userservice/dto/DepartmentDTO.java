package com.example.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDTO{

    private String id;
    private String name;
    private String description;
    @NotNull
    private String companyId;
}
