package com.example.paymentservice.validator;

import com.example.paymentservice.dto.PaymentDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class PaymentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PaymentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
    }

}
