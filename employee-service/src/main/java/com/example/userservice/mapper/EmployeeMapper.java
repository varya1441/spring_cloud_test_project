package com.example.userservice.mapper;


import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Override
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "company.id", target = "companyId")
    EmployeeDTO mapToDTO(Employee entity);

    @Override
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "companyId", target = "company.id")
    Employee mapToEntity(EmployeeDTO dto);
}
