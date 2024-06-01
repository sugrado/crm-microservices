package com.turkcell.crm.common.shared.dtos.accounts;

import java.time.LocalDateTime;

public record GetByIdAccountResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String status,
        String name,
        String number,
        int customerId,
        int typeId) {
}
