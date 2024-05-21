package com.turkcell.crm.account_service.business.dtos.responses.account_addresses;

public record GetAllByAccountIdResponse(
        int accountId,
        int id,
        int addressId
) {
}
