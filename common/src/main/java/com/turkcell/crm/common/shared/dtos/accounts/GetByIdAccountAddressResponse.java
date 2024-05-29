package com.turkcell.crm.common.shared.dtos.accounts;

public record GetByIdAccountAddressResponse(
        int id,
        int accountId,
        int addressId
) {
}
