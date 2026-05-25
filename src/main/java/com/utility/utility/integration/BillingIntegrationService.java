package com.utility.utility.integration;

import com.utility.utility.enums.BillStatus;
import com.utility.utility.model.Bill;
import com.utility.utility.model.Customer;
import com.utility.utility.repository.BillRepository;
import com.utility.utility.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillingIntegrationService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Simulates fetching bills from an external billing web service
    public void generateSimulatedBillsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return;

        // Check if customer already has bills so we don't duplicate
        List<Bill> existingBills = billRepository.findByCustomerId(customerId);
        if (!existingBills.isEmpty()) return;

        // Simulate 3 bills coming in from the external billing system
        BillStatus[] statuses = {
        	    BillStatus.PENDING_PAYMENT,
        	    BillStatus.PENDING_PAYMENT,
        	    BillStatus.PAID
        	};
        Double[] amounts = {85.50, 120.00, 95.75};
        LocalDate[] dueDates = {
                LocalDate.now().plusDays(14),
                LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(10)
        };

        for (int i = 0; i < 3; i++) {
            Bill bill = new Bill();
            bill.setCustomer(customer);
            bill.setAmount(amounts[i]);
            bill.setStatus(statuses[i]);
            bill.setDueDate(dueDates[i]);
            billRepository.save(bill);
        }

        System.out.println("✅ Billing Integration: 3 bills fetched and saved for customer " + customerId);
    }
}
