package com.example.userservice.mapper;


import com.example.userservice.dto.CompensationDTO;
import com.example.userservice.entity.Compensation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompensationMapper extends BaseMapper<Compensation, CompensationDTO> {

    @Override
    @Mapping(source = "employee.id", target = "employeeId")
    CompensationDTO mapToDTO(Compensation entity);

    @Override
    @Mapping(source = "employeeId", target = "employee.id")
    Compensation mapToEntity(CompensationDTO dto);
}
