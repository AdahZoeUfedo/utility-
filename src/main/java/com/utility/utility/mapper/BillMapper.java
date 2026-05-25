package com.utility.utility.mapper;

import com.utility.utility.dto.response.BillResponseDTO;
import com.utility.utility.model.Bill;

public class BillMapper {

    private BillMapper() {
    }

    public static BillResponseDTO toDTO(Bill bill) {

        return new BillResponseDTO(
                bill.getId(),
                bill.getAmount(),
                bill.getDueDate(),
                bill.getStatus(),

                bill.getUtilityAccount() != null
                        ? bill.getUtilityAccount().getId()
                        : null
        );
    }
}