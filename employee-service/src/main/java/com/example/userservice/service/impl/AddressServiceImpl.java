package com.example.userservice.service.impl;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.Company;
import com.example.userservice.entity.Employee;
import com.example.userservice.mapper.AddressMapper;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import com.example.userservice.service.CompanyService;
import com.example.userservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private EmployeeService employeeService;
    private CompanyService companyService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, EmployeeService employeeService, CompanyService companyService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @Override
    public Address findAddressById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class + "with id " + id));
    }

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = addressMapper.mapToEntity(addressDTO);


        UUID employeeId = address.getEmployee().getId();
        Employee employee = employeeService.findEmployeeById(employeeId);
        address.setEmployee(employee);

        UUID companyId = address.getCompany().getId();
        Company company = companyService.findCompanyById(companyId);
        address.setCompany(company);

        Address savedAddress = addressRepository.save(address);
        log.info("Address is saved");

        return addressMapper.mapToDTO(savedAddress);
    }
}
