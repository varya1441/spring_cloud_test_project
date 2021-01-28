package com.example.userservice.dto.payment;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class PaymentDTO {
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
    Map<UUID,List<CompensationPaymentDTO>> payments = new HashMap();

    public PaymentDTO(OffsetDateTime fromDate, OffsetDateTime toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

}
