package com.example.userservice.mapper;


import com.example.userservice.dto.EmployeeInfo;
import com.example.userservice.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class,DepartmentMapper.class,AddressMapper.class})
public interface EmployeeInfoMapper extends BaseMapper<Employee, EmployeeInfo> {

}
