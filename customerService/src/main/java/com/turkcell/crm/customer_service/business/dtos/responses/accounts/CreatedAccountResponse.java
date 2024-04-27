package com.turkcell.crm.customer_service.business.dtos.responses.accounts;

import java.time.LocalDateTime;

public record CreatedAccountResponse(
        int id,
        LocalDateTime createdDate,
        String status,
        String name,
        String number,
        int customerId,
        int typeId
) {
}
