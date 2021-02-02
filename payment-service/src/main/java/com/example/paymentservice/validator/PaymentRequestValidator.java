package com.example.paymentservice.validator;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.dto.PaymentRequest;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.utils.Converter;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.OffsetDateTime;

@Service
public class PaymentRequestValidator implements Validator {
    private final PaymentRepository paymentRepository;
    private final Converter converter;

    public PaymentRequestValidator(PaymentRepository paymentRepository, Converter converter) {
        this.paymentRepository = paymentRepository;
        this.converter = converter;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PaymentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "field.required");

        PaymentRequest paymentRequest = (PaymentRequest) obj;
        OffsetDateTime fromDate = converter.convertToOffset(paymentRequest.getFromDate());
        OffsetDateTime toDate = converter.convertToOffset(paymentRequest.getToDate());
        if (fromDate.isAfter(toDate)) {
            errors.rejectValue("from_to_dates", "invalid.date", new Object[]{OffsetDateTime.now()}
                    , "invalid as" + fromDate + " is after" + toDate);
        }
        if (checkIfRangeDatesExist(fromDate, toDate)) {
            errors.rejectValue("from_to_dates", "exists.date", new Object[]{OffsetDateTime.now()}
                    , "is already calculated from " + fromDate + " to " + toDate);
        }
    }

    private boolean checkIfRangeDatesExist(OffsetDateTime fromDate, OffsetDateTime toDate) {
        Payment payment = paymentRepository.findByFromDateAndToDate(fromDate, toDate);
        return payment != null;
    }
}
