package com.utility.utility.service;

import com.utility.utility.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(Long id);
}