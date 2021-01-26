package com.example.userservice.service.impl;

import com.example.userservice.entity.Compensation;
import com.example.userservice.entity.Department;
import com.example.userservice.repository.CompensationRepository;
import com.example.userservice.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CompensationServiceImpl implements CompensationService {
    private CompensationRepository compensationRepository;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    @Override
    public Compensation findCompensationById(String id) {
        return compensationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class + "with id " + id));
    }
}
