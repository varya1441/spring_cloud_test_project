package com.example.userservice.repository;

import com.example.userservice.entity.Compensation;
import com.example.userservice.entity.Compensation_;
import com.example.userservice.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.OffsetDateTime;

public class CompensationFiltration {
    public static Specification<Compensation> isFromTo(OffsetDateTime from, OffsetDateTime to) {
        return (root, query, cb) -> cb.between(root.get(Compensation_.EFFECTIVE_DATE), from, to);
    }
    public static Specification<Compensation> isFrom(OffsetDateTime from) {
        return (root, query, cb) -> cb.lessThan(root.get(Compensation_.EFFECTIVE_DATE), from);
    }
}