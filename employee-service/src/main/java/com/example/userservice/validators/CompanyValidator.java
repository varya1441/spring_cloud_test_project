package com.example.userservice.validators;

import com.example.userservice.dto.CompanyDTO;
import com.example.userservice.dto.DepartmentDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class CompanyValidator implements Validator {

        @Override
        public boolean supports(Class<?> aClass) {
            return CompanyDTO.class.equals(aClass);
        }

        @Override
        public void validate(Object obj, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required","name is null");

        }

}
