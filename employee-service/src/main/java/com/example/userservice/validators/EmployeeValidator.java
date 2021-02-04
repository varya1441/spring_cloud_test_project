package com.example.userservice.validators;


import com.example.userservice.dto.EmployeeDTO;
import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.time.OffsetDateTime;

@Service
public class EmployeeValidator implements General {
    private final CustomValidator customValidator;

    public EmployeeValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "firstName is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "lastName is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required", "login is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departmentId", "field.required", "departmentId is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyId", "field.required", "companyId is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hiringDate", "field.required", "hiringDate is null");


        EmployeeDTO employeeDTO = (EmployeeDTO) obj;
        OffsetDateTime hiringDate = employeeDTO.getHiringDate();
        OffsetDateTime terminationDate = employeeDTO.getTerminationDate();
        if (hiringDate != null) {
            customValidator.compareProperties(errors,
                    new Pair<>("hiringDate", hiringDate),
                    new Pair<>("now", OffsetDateTime.now()));
            if (terminationDate != null) {
                if (terminationDate != null) {
                    customValidator.compareProperties(errors,
                            new Pair<>("terminationDate", terminationDate),
                            new Pair<>("now", OffsetDateTime.now()));

                    customValidator.compareProperties(errors,
                            new Pair<>("hiringDate", hiringDate),
                            new Pair<>("terminationDate", terminationDate));
                }
            }
        }
    }
}
