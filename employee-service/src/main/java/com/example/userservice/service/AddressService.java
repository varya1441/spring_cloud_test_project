package com.example.userservice.service;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.entity.Address;

public interface AddressService {
    Address findAddressById(String id);

    AddressDTO saveAddress(AddressDTO addressDTO);
}
