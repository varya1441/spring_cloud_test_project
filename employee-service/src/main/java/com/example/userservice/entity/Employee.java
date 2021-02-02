package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
@NamedEntityGraph(
        name = "compensation-employee-graph",
        attributeNodes = {
                @NamedAttributeNode("compensations")
        }
)
@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", hiringDate=" + hiringDate +
                ", terminationDate=" + terminationDate +

                '}';
    }
}
