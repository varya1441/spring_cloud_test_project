package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@FeignClient(name = "employee-service")
//public interface EmployeeServiceFeignClient {
//
//    @GetMapping(value = "/{id}")
//    PaymentDTO getPaymentInfoByUserId(@PathVariable String id);
//}

