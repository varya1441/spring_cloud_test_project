package com.example.userservice.service;


import com.example.userservice.dto.CompanyDTO;
import com.example.userservice.entity.Company;

import java.util.UUID;

public interface CompanyService {

    Company findCompanyById(UUID id);

    CompanyDTO saveCompany(CompanyDTO company);
}