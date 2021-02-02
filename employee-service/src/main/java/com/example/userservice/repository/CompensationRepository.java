package com.example.userservice.repository;

import com.example.userservice.entity.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface CompensationRepository extends JpaRepository<Compensation, UUID> {
    @Query(value = "SELECT * FROM compensation" +
            " WHERE ((effective_date BETWEEN :fromDate AND :toDate)" +
            " or (effective_date in(select max(effective_date) from compensation" +
            " where effective_date<:fromDate))) and employee_id=:employeeId", nativeQuery = true)
    List<Compensation> getAllEmployeeCompensationsBetweenDates(@Param("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromDate,
                                          @Param("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toDate,
                                          @Param("employeeId") UUID employeeId);
}
