package com.utility.utility.service;

import com.utility.utility.dto.response.PaymentResponseDTO;
import com.utility.utility.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    List<PaymentResponseDTO> findAllPayments();
}