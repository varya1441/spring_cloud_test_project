package com.example.authserver.controller;

import com.example.authserver.dto.Employee;
import com.example.authserver.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class AuthController {
//    @PostMapping(value = "/register")
//    public ResponseEntity<Employee> signUp(@RequestBody RegisterRequest rRequest, BindingResult bindingResult) {
//        regUserValidator.validate(rRequest, bindingResult);
//        if (bindingResult.hasErrors()) {
//            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
//        }
//        return ResponseEntity.ok(authService.registration(rRequest));
//    }
//
//}
