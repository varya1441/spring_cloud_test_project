package com.example.userservice.validators;

import com.netflix.discovery.shared.Pair;
import org.springframework.validation.Errors;

public interface GeneralComparisonValidator<S, T> {
    void compareProperties(Errors errors, Pair<S, T> data1, Pair<S, T> data2);
}

