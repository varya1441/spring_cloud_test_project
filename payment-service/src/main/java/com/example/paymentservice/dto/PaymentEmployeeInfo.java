package com.example.paymentservice.dto;

import com.example.paymentservice.entity.PaymentMethod;
import com.example.paymentservice.entity.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class PaymentEmployeeInfo {

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
