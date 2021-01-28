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
    private static final int ID_LENGTH = 36;

    public AddressValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeId", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyId", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movingDate", "field.required");

        AddressDTO addressDTO = (AddressDTO) obj;
        OffsetDateTime movingDate = addressDTO.getMovingDate();
        OffsetDateTime leavingDate = addressDTO.getLeavingDate();

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

        if (addressDTO.getCompanyId().toString().length() < ID_LENGTH ||
                addressDTO.getEmployeeId().toString().length() < ID_LENGTH) {
            errors.rejectValue("id", "too.short", new Object[]{"'id'"}, "id length should be > 6");
        }
    }
}
