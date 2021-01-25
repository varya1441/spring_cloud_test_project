package com.example.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class EmployeeController {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/nastya")
    public String getEmployee() {
        return "cpanel";
    }
    @RequestMapping("/varya")
    public String getEmployeea(Model m) {
        m.addAttribute("name","varya");
        return "varya";
    }

    @RequestMapping("/user")
    @ResponseBody
    public String user(Principal user) {
        return user.toString();

    }
}
