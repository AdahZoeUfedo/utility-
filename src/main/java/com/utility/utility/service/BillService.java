package com.utility.utility.service;

import com.utility.utility.dto.response.BillResponseDTO;
import com.utility.utility.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {

    List<Bill> getBillsByCustomerId(Long customerId);

    List<BillResponseDTO> getBillDTOsByCustomerId(Long customerId);

    Optional<Bill> findById(Long id);

    Bill save(Bill bill);

    boolean isWithin30Days(Bill bill);

    void markAsDisputed(Bill bill);

    void markAsPaid(Bill bill);

    Bill getBillOrThrow(Long id);
}