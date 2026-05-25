package com.utility.utility.dao.impl;

import com.utility.utility.dao.PaymentDAO;
import com.utility.utility.model.Payment;
import com.utility.utility.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    private final PaymentRepository paymentRepository;

    public PaymentDAOImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}