package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "employee-service")
public interface EmployeeServiceFeignClient {

    @PostMapping("/compensation/payment")
    PaymentDTO getPayments(@RequestBody PaymentRequest paymentRequest);
}

