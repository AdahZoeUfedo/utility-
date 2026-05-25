package com.utility.utility.dao.impl;

import com.utility.utility.dao.BillDAO;
import com.utility.utility.model.Bill;
import com.utility.utility.repository.BillRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BillDAOImpl implements BillDAO {

    private final BillRepository billRepository;

    public BillDAOImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {
        billRepository.delete(bill);
    }

    @Override
    public List<Bill> findByCustomerId(Long customerId) {
        return billRepository.findByCustomerId(customerId);
    }
}