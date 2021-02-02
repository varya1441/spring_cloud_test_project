package com.example.userservice.service.impl;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.Company;
import com.example.userservice.entity.Employee;
import com.example.userservice.exception.ItemNotFoundException;
import com.example.userservice.mapper.AddressMapper;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import com.example.userservice.service.CompanyService;
import com.example.userservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private EmployeeService employeeService;
    private CompanyService companyService;


    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, EmployeeService employeeService, CompanyService companyService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @Override
    public Address findAddressById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(Address.class + "with id " + id + " not found");
                    return new EntityNotFoundException(Address.class + "with id " + id);
                });
    }

    @Override
    public List<AddressDTO> findAddressByEmployeeId(UUID employeeId) {
        List<Address> addresses = addressRepository.findAllByEmployeeId(employeeId);

        if (!CollectionUtils.isEmpty(addresses)) {
            log.info("found " + addresses.size() + "addresses for employee" + employeeId);
            return addresses.stream().map(addressMapper::mapToDTO).collect(Collectors.toList());
        }

        log.error("Employee is homeless");
        throw new ItemNotFoundException("Employee is homeless");
    }

    @Override
    @Transactional
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
