package com.example.userservice.repository;

import com.example.userservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> , JpaSpecificationExecutor {
    Employee findByLogin(String login);

}
