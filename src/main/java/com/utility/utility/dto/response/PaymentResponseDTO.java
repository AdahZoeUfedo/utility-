package com.utility.utility.dto.response;

import com.utility.utility.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponseDTO(

        Long id,
        Double amount,
        PaymentStatus status,
        LocalDateTime paymentDate,
        Long billId

) {
}