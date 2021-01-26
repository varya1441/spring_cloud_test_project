package com.example.userservice.repository;

import com.example.userservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByLogin(String login);
}
