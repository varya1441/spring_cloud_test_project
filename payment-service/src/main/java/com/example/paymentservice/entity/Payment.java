package com.example.paymentservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer wages;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Timestamp fromDate;
    private Timestamp toDate;
    private Integer workingHours;
    private Integer rate;
}
