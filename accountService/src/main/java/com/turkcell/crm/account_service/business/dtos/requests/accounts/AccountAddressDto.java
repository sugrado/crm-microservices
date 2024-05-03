package com.turkcell.crm.account_service.business.dtos.requests.accounts;

import jakarta.validation.constraints.NotNull;

public record AccountAddressDto(
        @NotNull
        int addressId
) {
}
