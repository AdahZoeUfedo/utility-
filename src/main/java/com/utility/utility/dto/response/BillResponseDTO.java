package com.utility.utility.dto.response;

import com.utility.utility.enums.BillStatus;

import java.time.LocalDate;

public record BillResponseDTO(

        Long id,
        Double amount,
        LocalDate dueDate,
        BillStatus status,
        Long utilityAccountId

) {
}