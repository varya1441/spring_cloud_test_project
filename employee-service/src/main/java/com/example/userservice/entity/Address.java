package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String state;
    private String city;
    private OffsetDateTime movingDate;
    private OffsetDateTime leavingDate;
    private String zipCode;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
}
