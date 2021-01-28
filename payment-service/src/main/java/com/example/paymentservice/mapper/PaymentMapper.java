package com.example.paymentservice.mapper;


import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseMapper<Payment, PaymentDTO> {

}
