package com.utility.utility.service;

import com.utility.utility.model.Bill;
import com.utility.utility.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getBillsByCustomerId(Long customerId) {
        return billRepository.findByCustomerId(customerId);
    }

    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public boolean isWithin30Days(Bill bill) {
        return bill.getDueDate().isAfter(LocalDate.now().minusDays(30));
    }


    public void markAsDisputed(Bill bill) {
        bill.setStatus("DISPUTED");
        billRepository.save(bill);
    }

    public void markAsPaid(Bill bill) {
       bill.setStatus("PAID");
        billRepository.save(bill);
    }
}