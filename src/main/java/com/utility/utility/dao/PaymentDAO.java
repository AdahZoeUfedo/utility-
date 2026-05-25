package com.utility.utility.dao;

import com.utility.utility.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    List<Payment> findAll();
}