package com.example.userservice.repository;

import com.example.userservice.entity.Compensation;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CompensationRepository extends JpaRepository<Compensation, UUID>, JpaSpecificationExecutor {

}
