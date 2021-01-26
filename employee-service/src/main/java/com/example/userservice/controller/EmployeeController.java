package com.example.userservice.controller;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.service.AddressService;
import com.example.userservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private AddressService addressService;

    public EmployeeController(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<EmployeeDTO> getByLogin(@PathVariable String login) {
        return new ResponseEntity<>(employeeService.findByLogin(login), HttpStatus.OK);
    }
    @RequestMapping("/user")
    @ResponseBody
    public String user(Principal user) {
        System.out.println(user);
        return user.getName();

    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> saveUserAddress(@RequestBody AddressDTO addressDTO) {

        return new ResponseEntity<>(addressService.saveAddress(addressDTO), HttpStatus.OK);
    }
}
