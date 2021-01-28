package com.example.userservice.validators;


import com.example.userservice.dto.EmployeeDTO;
import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departmentId", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyId", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hiringDate", "field.required");


        EmployeeDTO employeeDTO = (EmployeeDTO) obj;
        OffsetDateTime hiringDate = employeeDTO.getHiringDate();
        OffsetDateTime terminationDate = employeeDTO.getTerminationDate();

        customValidator.compareProperties(errors,
                new Pair<>("hiringDate", hiringDate),
                new Pair<>("now", OffsetDateTime.now()));
        if (terminationDate != null) {
            customValidator.compareProperties(errors,
                    new Pair<>("terminationDate", terminationDate),
                    new Pair<>("now", OffsetDateTime.now()));

            customValidator.compareProperties(errors,
                    new Pair<>("hiringDate", hiringDate),
                    new Pair<>("terminationDate", terminationDate));
        }

        if (employeeDTO.getCompanyId().toString().length() < ID_LENGTH ||
                employeeDTO.getDepartmentId().toString().length() < ID_LENGTH) {
            errors.rejectValue("id", "too.short", new Object[]{"'id'"}, "id length should be > 6");
        }
    }
}
