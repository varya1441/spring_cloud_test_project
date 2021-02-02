package com.example.userservice.dto;

import com.example.userservice.dto.payment.Payment;
import com.example.userservice.entity.Gender;
import com.example.userservice.entity.Status;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeeInfo {
    private UUID id;
    private String firstName;
    private String lastName;
    private String login;
    private Gender gender;
    private Status status;
    private OffsetDateTime hiringDate;
    private OffsetDateTime terminationDate;
    private DepartmentDTO department;
    private CompanyDTO company;
    List<AddressDTO> addresses;
    List<Payment> payments;

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", hiringDate=" + hiringDate +
                ", terminationDate=" + terminationDate +
                ", department=" + department +
                ", company=" + company +
                ", addresses=" + addresses +
                ", payments=" + payments +
                '}';
    }
}
