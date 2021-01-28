package com.example.userservice.validators;

import com.netflix.discovery.shared.Pair;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.OffsetDateTime;

@Component
class CustomValidator implements GeneralComparisonValidator<String, OffsetDateTime> {

    @Override
    public void compareProperties(Errors errors, Pair<String, OffsetDateTime> data1, Pair<String, OffsetDateTime> data2) {
        if (data1.second().compareTo(data2.second()) > 0) {
            errors.rejectValue(data1.first(), "invalid.date", new Object[]{OffsetDateTime.now()}
                    , "invalid as" + data1.first() + "more then" + data2.second());
        }
    }
}
