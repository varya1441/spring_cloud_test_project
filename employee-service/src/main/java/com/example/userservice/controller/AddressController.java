package com.example.userservice.controller;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.exception.RestExceptionHandler;
import com.example.userservice.exception.ValidationException;
import com.example.userservice.service.AddressService;
import com.example.userservice.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AddressController {
    private AddressService addressService;
    private AddressValidator addressValidator;

    @Autowired
    public AddressController(AddressService addressService, AddressValidator addressValidator) {
        this.addressService = addressService;
        this.addressValidator = addressValidator;
    }

    @PostMapping("/address")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AddressDTO> saveUserAddress(@RequestBody @Valid AddressDTO addressDTO, BindingResult bindingResult) {
        addressValidator.validate(addressDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }

        return ResponseEntity.ok(addressService.saveAddress(addressDTO));
    }

}
