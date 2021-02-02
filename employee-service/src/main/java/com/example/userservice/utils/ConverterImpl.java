package com.example.userservice.utils;

import com.example.userservice.exception.ItemNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class ConverterImpl implements Converter {
    @Override
    public OffsetDateTime convertToOffset(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        }
        throw new ItemNotFoundException("item localdatatime is null");
    }
}
