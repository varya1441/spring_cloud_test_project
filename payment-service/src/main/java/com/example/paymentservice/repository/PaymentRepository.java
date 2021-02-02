package com.example.paymentservice.repository;

import com.example.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>, JpaSpecificationExecutor {
    @Query(value = "SELECT * FROM payment.public.payment where deleted=false", nativeQuery = true)
    List<Payment> findAllByEmployeeId(UUID id);

    @Query(value = "SELECT * FROM payment.public.payment where deleted=false", nativeQuery = true)
    Payment findByFromDateAndToDate(OffsetDateTime fromDate, OffsetDateTime toDate);

    @Query(value = "SELECT * FROM payment.public.payment WHERE from_date >= :fromDate AND to_date<=:toDate and employee_id=:employeeId", nativeQuery = true)
    List<Payment> getEmployeePaymentsForPeriod(@Param("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromDate,
                                               @Param("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toDate,
                                               @Param("employeeId") UUID employeeId);
}

