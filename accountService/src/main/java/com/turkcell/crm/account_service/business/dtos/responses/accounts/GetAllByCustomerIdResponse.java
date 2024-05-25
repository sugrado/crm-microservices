package com.turkcell.crm.account_service.business.dtos.responses.accounts;

import com.turkcell.crm.account_service.entities.enums.Status;

public record GetAllByCustomerIdResponse(
        int id,
        Status status,
        String name,
        String number,
        int typeId
) {
}
