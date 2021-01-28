package com.example.userservice.service.impl;

import com.example.userservice.dto.CompanyDTO;
import com.example.userservice.entity.Company;
import com.example.userservice.mapper.CompanyMapper;
import com.example.userservice.repository.CompanyRepository;
import com.example.userservice.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findCompanyById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class + "with id " + id));
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.mapToEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        log.info("Company is saved");

        return companyMapper.mapToDTO(savedCompany);
    }
}
