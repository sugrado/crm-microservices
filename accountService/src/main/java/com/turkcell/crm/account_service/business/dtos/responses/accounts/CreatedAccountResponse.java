package com.turkcell.crm.account_service.business.dtos.responses.accounts;

import com.turkcell.crm.account_service.entities.enums.Status;

import java.time.LocalDateTime;

public record CreatedAccountResponse(
        int id,
        LocalDateTime createdDate,
        Status status,
        String name,
        String number,
        int customerId,
        int typeId
) {
}
