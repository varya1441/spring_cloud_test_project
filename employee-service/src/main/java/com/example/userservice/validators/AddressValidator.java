package com.example.userservice.validators;

import com.example.userservice.dto.AddressDTO;
import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.OffsetDateTime;

@Service
public class AddressValidator implements Validator {
    private final CustomValidator customValidator;

    public AddressValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "field.required","addressLine1 is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "field.required","employeeId is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyId", "field.required","companyId is null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movingDate", "field.required","movingDate is null");

        AddressDTO addressDTO = (AddressDTO) obj;
        OffsetDateTime movingDate = addressDTO.getMovingDate();
        OffsetDateTime leavingDate = addressDTO.getLeavingDate();
        if (movingDate != null) {
            customValidator.compareProperties(errors,
                    new Pair<>("MovingDate", movingDate),
                    new Pair<>("now", OffsetDateTime.now()));
            if (leavingDate != null) {
                customValidator.compareProperties(errors,
                        new Pair<>("LeavingDate", leavingDate),
                        new Pair<>("now", OffsetDateTime.now()));

                customValidator.compareProperties(errors,
                        new Pair<>("MovingDate", movingDate),
                        new Pair<>("LeavingDate", leavingDate));
            }
        }

    }
}
