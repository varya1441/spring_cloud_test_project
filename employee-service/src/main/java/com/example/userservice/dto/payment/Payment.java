package com.example.userservice.dto.payment;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Payment {

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

}
