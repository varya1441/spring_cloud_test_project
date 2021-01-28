package com.example.userservice.controller;

import com.example.userservice.dto.payment.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface EmployeeServiceFeignClient {

    @GetMapping(value = "/payment/{id}")
    PaymentDTO getPayment(@RequestBody PaymentDTO paymentDTO, @PathVariable String id);

}