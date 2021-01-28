package com.example.userservice.validators;

import com.netflix.discovery.shared.Pair;
import org.springframework.validation.Errors;

import java.util.HashMap;

public interface GeneralFieldValidator<S, T> {
    void checkIfRequiredFieldFill(Errors errors,HashMap<S,T> fields);
}

