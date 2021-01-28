package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
@NamedEntityGraph(
        name = "compensation-employee-graph",
        attributeNodes = {
                @NamedAttributeNode("employee")
        }
)
@Entity
@Data
public class Compensation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private OffsetDateTime effectiveDate;
    private Double salaryPerHour;
    private Double salaryPerWeek;
    private Double salaryPerMonth;
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
