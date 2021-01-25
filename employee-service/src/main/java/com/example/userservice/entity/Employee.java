package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    private OffsetDateTime hiringDate;
    private OffsetDateTime terminationDate;

    @OneToMany(mappedBy = "employee")
    private List<Address> addresses;
    @OneToMany(mappedBy = "employee")
    private List<Compensation> compensations;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Company company;

}
