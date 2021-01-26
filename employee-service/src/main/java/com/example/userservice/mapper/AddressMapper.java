package com.example.userservice.mapper;


import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {

    @Override
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "company.id", target = "companyId")
    AddressDTO mapToDTO(Address entity);

    @Override
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "companyId", target = "company.id")
    Address mapToEntity(AddressDTO dto);
}
