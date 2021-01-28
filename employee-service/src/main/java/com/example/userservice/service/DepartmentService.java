package com.example.userservice.service;

import com.example.userservice.dto.DepartmentDTO;
import com.example.userservice.entity.Department;

import java.util.UUID;


public interface DepartmentService {
    Department findDepartmentById(UUID id);

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
}