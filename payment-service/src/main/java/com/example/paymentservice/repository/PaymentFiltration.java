package com.example.paymentservice.repository;//package com.example.userservice.repository;


import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.Payment_;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
//todo
public class PaymentFiltration {
    public static Specification<Payment> isFromTo(OffsetDateTime from, OffsetDateTime to) {
        return (root, query, cb) -> cb.between(root.get(Payment_.fromDate), from, to);
    }

    public static Specification<Payment> isFrom(OffsetDateTime from) {
        return (root, query, cb) -> cb.lessThan(root.get(Payment_.toDate), from);
    }
}