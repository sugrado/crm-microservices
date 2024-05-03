package com.turkcell.crm.account_service.business.dtos.responses.account_addresses;

public record CreatedAccountAddressResponse(
        int id,
        int addressId,
        int accountId
) {
}
