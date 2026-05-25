package com.utility.utility.mapper;

import com.utility.utility.dto.response.PaymentResponseDTO;
import com.utility.utility.model.Payment;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentResponseDTO toDTO(Payment payment) {

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentDate(),

                payment.getBill() != null
                        ? payment.getBill().getId()
                        : null
        );
    }
}