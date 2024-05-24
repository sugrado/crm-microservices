package com.turkcell.crm.account_service.business.dtos.responses.account_types;

import java.time.LocalDateTime;

public record DeletedAccountTypeResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime deletedDate,
        String name
) {
}
