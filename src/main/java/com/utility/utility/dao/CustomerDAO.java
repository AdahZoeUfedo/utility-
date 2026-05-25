package com.utility.utility.dao;

import com.utility.utility.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    Customer save(Customer customer);

    void delete(Customer customer);
}