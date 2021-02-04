package com.example.userservice.validators;

import com.example.userservice.dto.AddressDTO;
import com.example.userservice.dto.CompensationDTO;
import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effectiveDate", "field.required", "effectiveDate is null ");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salaryPerHour", "field.required", "salaryPerHour is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "field.required", "employeeId is null");

        CompensationDTO compensationDTO = (CompensationDTO) obj;
        if (compensationDTO.getEffectiveDate() != null) {
            customValidator.compareProperties(errors,
                    new Pair<>("EffectiveDate", compensationDTO.getEffectiveDate()),
                    new Pair<>("now", OffsetDateTime.now()));
        }
    }

}
