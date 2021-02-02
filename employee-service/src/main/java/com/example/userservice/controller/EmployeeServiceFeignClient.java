package com.example.userservice.controller;

import com.example.userservice.dto.payment.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "payment-service")
public interface EmployeeServiceFeignClient {

    @GetMapping(value = "/payments/{employeeId}")
    List<Payment> getPayment(@PathVariable UUID employeeId);

}