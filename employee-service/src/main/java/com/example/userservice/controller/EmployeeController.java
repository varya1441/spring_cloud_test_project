package com.example.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

    @RequestMapping("/nastya")
    @PreAuthorize("hasRole('ADMIN') ")
    public String getEmployee() {
        return "hello";
    }
    @RequestMapping("/varya")
    public String getEmployeea() {
        return "hello";
    }
}
