package com.utility.utility.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequestDTO(

        @NotNull(message = "Bill ID is required")
        Long billId,

        @NotNull(message = "Payment amount is required")
        @Positive(message = "Amount must be positive")
        Double amount

) {
}