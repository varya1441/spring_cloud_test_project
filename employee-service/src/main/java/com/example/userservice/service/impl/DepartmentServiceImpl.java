package com.example.userservice.service.impl;

import com.example.userservice.dto.DepartmentDTO;
import com.example.userservice.entity.Company;
import com.example.userservice.entity.Department;
import com.example.userservice.mapper.DepartmentMapper;
import com.example.userservice.repository.DepartmentRepository;
import com.example.userservice.service.CompanyService;
import com.example.userservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final CompanyService companyService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, CompanyService companyService) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.companyService = companyService;
    }

    public Department findDepartmentById(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class + "with id " + id));
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.mapToEntity(departmentDTO);

        UUID companyId = department.getCompany().getId();
        Company company = companyService.findCompanyById(companyId);
        department.setCompany(company);

        Department savedDepartment = departmentRepository.save(department);
        log.info("Department is saved");

        return departmentMapper.mapToDTO(savedDepartment);
    }
}
