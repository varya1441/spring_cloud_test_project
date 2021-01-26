package com.example.authserver.client;

import com.example.authserver.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "employee-service")
public interface EmployeeServiceFeignClient {

    @GetMapping(value = "/{id}")
    Employee getEmployee(@PathVariable String id);

    @GetMapping(value = "/login/{login}")
    Employee getByLogin(@PathVariable String login);

    @PostMapping(value = "")
    Employee save(@RequestBody Employee user);
}