package com.example.paymentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PaymentController {
    @Value("${spring.datasource.username}")
    private String dpName;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/")
    public String getDbName(Model m) {
        m.addAttribute("welcome",dpName);
        return "home";
    }
}
