package com.turkcell.crm.account_service.business.dtos.responses.account_addresses;

import java.time.LocalDateTime;

public record DeletedAcountAddressResponse(
        int id,
        LocalDateTime deletedDate,
        int addressId,
        int accountId
) {
}
