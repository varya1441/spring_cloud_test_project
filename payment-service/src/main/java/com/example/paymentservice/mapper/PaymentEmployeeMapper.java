package com.example.paymentservice.mapper;


import com.example.paymentservice.dto.PaymentEmployeeInfo;
import com.example.paymentservice.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEmployeeMapper extends BaseMapper<Payment, PaymentEmployeeInfo> {


}
