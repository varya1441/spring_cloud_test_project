package com.example.userservice.service;

import com.example.userservice.entity.Compensation;

public interface CompensationService {
    Compensation findCompensationById(String id);
}
