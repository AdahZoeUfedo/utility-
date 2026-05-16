package com.utility.utility.service;

import com.utility.utility.model.Bill;
import com.utility.utility.model.Dispute;
import com.utility.utility.repository.DisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DisputeService {

    @Autowired
    private DisputeRepository disputeRepository;

    @Autowired
    private BillService billService;

    public String submitDispute(Long billId, String reason) {
        Optional<Bill> billOpt = billService.findById(billId);

        if (billOpt.isEmpty()) {
            return "Bill not found";
        }

        Bill bill = billOpt.get();

        if (!billService.isWithin30Days(bill)) {
            return "Dispute window has expired (30 days)";
        }

        Dispute dispute = new Dispute();
        dispute.setBill(bill);
        dispute.setReason(reason);
        dispute.setSubmittedDate(LocalDate.now());
        disputeRepository.save(dispute);

        billService.markAsDisputed(bill);
        return "Dispute submitted successfully";
    }
}