package com.example.paymentservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer wages;
    private Object paymentMethod;
    private Timestamp fromDate;
    private Timestamp toDate;
    private Integer workingHours;
    private Integer rate;
}
