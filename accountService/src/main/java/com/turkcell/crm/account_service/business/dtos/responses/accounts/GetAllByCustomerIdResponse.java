package com.turkcell.crm.account_service.business.dtos.responses.accounts;

import com.turkcell.crm.account_service.entities.enums.Status;

import java.time.LocalDateTime;

public record GetAllByCustomerIdResponse(
        int id,
        Status status,
        String name,
        String number,
        int typeId
) {
}
