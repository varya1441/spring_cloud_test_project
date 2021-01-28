package com.example.userservice.service;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;

import java.util.UUID;

public interface AddressService {
    Address findAddressById(UUID id);

    AddressDTO saveAddress(AddressDTO addressDTO);
}
