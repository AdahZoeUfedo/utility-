package com.utility.utility.service.impl;

import com.utility.utility.dao.CustomerDAO;
import com.utility.utility.model.Customer;
import com.utility.utility.service.CustomerService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl
        implements CustomerService {

    private final CustomerDAO customerDAO;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(
            CustomerDAO customerDAO,
            PasswordEncoder passwordEncoder
    ) {

        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer save(Customer customer) {

        customer.setPassword(
                passwordEncoder.encode(
                        customer.getPassword()
                )
        );

        return customerDAO.save(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {

        return customerDAO.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(Long id) {

        return customerDAO.findById(id);
    }
}