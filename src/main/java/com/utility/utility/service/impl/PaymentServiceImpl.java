package com.utility.utility.service.impl;

import com.utility.utility.dao.PaymentDAO;
import com.utility.utility.dto.response.PaymentResponseDTO;
import com.utility.utility.exception.PaymentFailedException;
import com.utility.utility.mapper.PaymentMapper;
import com.utility.utility.model.Payment;
import com.utility.utility.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public Payment save(Payment payment) {

        if (payment.getAmount() <= 0) {

            throw new PaymentFailedException(
                    "Payment amount must be greater than zero."
            );
        }

        return paymentDAO.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentDAO.findById(id);
    }

    @Override
    public List<PaymentResponseDTO> findAllPayments() {

        return paymentDAO.findAll()
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }
    
}