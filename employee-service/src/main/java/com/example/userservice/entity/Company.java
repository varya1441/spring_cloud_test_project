package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Address> address;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
    @OneToMany(mappedBy = "company")
    private List<Department> departments;
}
