package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Timestamp effectiveDate;
    private Double salaryPerHour;
    private Double salaryPerWeek;
    private Double salaryPerMonth;
    @ManyToOne
    private Employee employee;
}