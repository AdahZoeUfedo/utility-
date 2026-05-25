package com.utility.utility.dao;

import com.utility.utility.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillDAO {

    List<Bill> findAll();

    Optional<Bill> findById(Long id);

    Bill save(Bill bill);

    void delete(Bill bill);

    List<Bill> findByCustomerId(Long customerId);
}