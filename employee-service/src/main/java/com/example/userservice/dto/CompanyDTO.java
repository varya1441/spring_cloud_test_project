package com.example.userservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CompanyDTO {
    private UUID id;
    private String name;
}
