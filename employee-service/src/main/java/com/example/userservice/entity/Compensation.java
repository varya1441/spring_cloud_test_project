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
    private Integer salaryPerHour;
    private Integer salaryPerWeek;
    private Integer salaryPerMonth;
    @ManyToOne
    private Employee employee;
}
