package com.example.userservice.validators;

import com.example.userservice.dto.DepartmentDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class DepartmentValidator implements General {

        @Override
        public boolean supports(Class<?> aClass) {
            return DepartmentDTO.class.equals(aClass);
        }

        @Override
        public void validate(Object obj, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required"," name is null");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyId", "field.required","companyId is null");
        }

}
