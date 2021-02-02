package com.example.paymentservice.repository;


import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.Payment_;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.UUID;


public class PaymentFiltration {

    public static Specification<Payment> isFromTo(OffsetDateTime from, OffsetDateTime to) {
        return (root, query, cb) -> cb.and(cb.greaterThan(root.get(Payment_.fromDate), from), cb.lessThan(root.get(Payment_.toDate), to));
    }

    public static Specification<Payment> isEqualEmployeeId(UUID employeeId) {
        return (root, query, cb) -> cb.equal(root.get(Payment_.employeeId), employeeId);
    }

    public static Specification<Payment> isNotDeleted() {
        return (root, query, cb) -> cb.notEqual(root.get(Payment_.deleted), true);
    }
}