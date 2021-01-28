package com.example.userservice.mapper;


import com.example.userservice.dto.AddressDTO;
import com.example.userservice.dto.CompanyDTO;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<Company, CompanyDTO> {

}
