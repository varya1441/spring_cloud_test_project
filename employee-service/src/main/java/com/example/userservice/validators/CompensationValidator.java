package com.example.userservice.validators;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.dto.CompensationDTO;
import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.OffsetDateTime;

@Service
public class CompensationValidator implements General {
    private final CustomValidator customValidator;

    public CompensationValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effectiveDate", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salaryPerHour", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "field.required");

        CompensationDTO compensationDTO = (CompensationDTO) obj;
        customValidator.compareProperties(errors,
                new Pair<>("EffectiveDate",compensationDTO.getEffectiveDate()),
                new Pair<>("now",OffsetDateTime.now()));

        if (compensationDTO.getEmployeeId().toString().length() < ID_LENGTH) {
            errors.rejectValue("id", "too.short", new Object[]{"'employeeId'"}, "id length should be > 6");
        }
    }

}
