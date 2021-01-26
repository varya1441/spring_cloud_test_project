package com.example.userservice.repository;

import com.example.userservice.entity.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompensationRepository extends JpaRepository<Compensation, String> {
}
