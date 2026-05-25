package com.utility.utility.service.impl;

import com.utility.utility.dao.BillDAO;
import com.utility.utility.dto.response.BillResponseDTO;
import com.utility.utility.enums.BillStatus;
import com.utility.utility.exception.ResourceNotFoundException;
import com.utility.utility.mapper.BillMapper;
import com.utility.utility.model.AuditLog;
import com.utility.utility.model.Bill;
import com.utility.utility.service.AuditLogService;
import com.utility.utility.service.BillService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillDAO billDAO;
    private final AuditLogService auditLogService;

    public BillServiceImpl(BillDAO billDAO,
                           AuditLogService auditLogService) {

        this.billDAO = billDAO;
        this.auditLogService = auditLogService;
    }

    @Override
    public List<Bill> getBillsByCustomerId(Long customerId) {

        return billDAO.findByCustomerId(customerId);
    }

    @Override
    public List<BillResponseDTO> getBillDTOsByCustomerId(Long customerId) {

        return billDAO.findByCustomerId(customerId)
                .stream()
                .map(BillMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<Bill> findById(Long id) {

        return billDAO.findById(id);
    }

    @Override
    public Bill save(Bill bill) {

        return billDAO.save(bill);
    }

    @Override
    public boolean isWithin30Days(Bill bill) {

        return bill.getDueDate()
                .isAfter(LocalDate.now().minusDays(30));
    }

    @Override
    public void markAsDisputed(Bill bill) {

        bill.setStatus(BillStatus.DISPUTED);

        billDAO.save(bill);
    }

    @Override
    public void markAsPaid(Bill bill) {

        bill.setStatus(BillStatus.PAID);

        AuditLog auditLog = new AuditLog();

        auditLog.setAction("BILL_PAYMENT");

        auditLog.setPerformedBy(
                bill.getUtilityAccount()
                        .getCustomer()
                        .getEmail()
        );

        auditLog.setTimestamp(LocalDateTime.now());

        auditLog.setDetails(
                "Bill with ID "
                        + bill.getId()
                        + " was marked as PAID"
        );

        auditLogService.saveLog(auditLog);

        billDAO.save(bill);
    }

    @Override
    public Bill getBillOrThrow(Long id) {

        return billDAO.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bill not found with ID: " + id
                        )
                );
    }
}