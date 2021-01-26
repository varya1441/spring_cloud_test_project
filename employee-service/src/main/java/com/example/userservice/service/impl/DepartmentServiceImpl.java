package com.example.userservice.service.impl;

import com.example.userservice.entity.Company;
import com.example.userservice.entity.Department;
import com.example.userservice.repository.DepartmentRepository;
import com.example.userservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department findDepartmentById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class + "with id " + id));
    }
}
