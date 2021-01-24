package com.example.paymentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PaymentController {
    @Value("${spring.datasource.username}")
    private String dpName;

    @RequestMapping("/")
    public String getDbName() {
        return dpName;
    }
}
