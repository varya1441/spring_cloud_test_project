package com.example.paymentservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private Double wages;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private Status status;
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
    private Integer workingHours;
    private Double rate;
    private UUID employeeId;
    private boolean deleted = false;

    public Payment(Double wages, PaymentMethod paymentMethod, Status status, OffsetDateTime fromDate, OffsetDateTime toDate, Integer workingHours, Double rate, UUID employeeId) {
        this.wages = wages;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.workingHours = workingHours;
        this.rate = rate;
        this.employeeId = employeeId;
    }

    public Payment(UUID id, Double wages, PaymentMethod paymentMethod, Status status, OffsetDateTime fromDate, OffsetDateTime toDate, Integer workingHours, Double rate, UUID employeeId) {
        this.id = id;
        this.wages = wages;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.workingHours = workingHours;
        this.rate = rate;
        this.employeeId = employeeId;
    }

    public Payment() {

    }
}
