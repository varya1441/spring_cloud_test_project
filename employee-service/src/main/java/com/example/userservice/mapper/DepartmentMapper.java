package com.example.userservice.mapper;


import com.example.userservice.dto.DepartmentDTO;
import com.example.userservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends BaseMapper<Department, DepartmentDTO> {

    @Override
    @Mapping(source = "company.id", target = "companyId")
    DepartmentDTO mapToDTO(Department entity);

    @Override
    @Mapping(source = "companyId", target = "company.id")
    Department mapToEntity(DepartmentDTO dto);
}
