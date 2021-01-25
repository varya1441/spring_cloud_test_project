package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;
    @ManyToOne
    private Company company;
}
