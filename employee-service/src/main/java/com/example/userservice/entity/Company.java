package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Address> address;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
    @OneToMany(mappedBy = "company")
    private List<Department> departments;
}
