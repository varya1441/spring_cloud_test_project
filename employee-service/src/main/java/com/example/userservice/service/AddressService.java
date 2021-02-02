package com.example.userservice.service;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address findAddressById(UUID id);
    List<AddressDTO> findAddressByEmployeeId(UUID employeeId);

    AddressDTO saveAddress(AddressDTO addressDTO);
}
