package com.example.userservice.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public interface Converter {
    OffsetDateTime convertToOffset(LocalDateTime localDateTime);
}
