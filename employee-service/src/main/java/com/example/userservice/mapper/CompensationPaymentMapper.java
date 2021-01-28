package com.example.userservice.mapper;


import com.example.userservice.dto.payment.CompensationPaymentDTO;
import com.example.userservice.entity.Compensation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompensationPaymentMapper extends BaseMapper<Compensation, CompensationPaymentDTO> {

}
