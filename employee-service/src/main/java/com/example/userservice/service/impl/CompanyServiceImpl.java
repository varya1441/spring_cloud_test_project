package com.example.userservice.service.impl;

import com.example.userservice.entity.Company;
import com.example.userservice.repository.CompanyRepository;
import com.example.userservice.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findCompanyById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class + "with id " + id));
    }
}
