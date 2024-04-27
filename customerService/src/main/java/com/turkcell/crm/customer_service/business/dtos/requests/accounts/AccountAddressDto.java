package com.turkcell.crm.customer_service.business.dtos.requests.accounts;

import jakarta.validation.constraints.NotNull;

public record AccountAddressDto(
        @NotNull
        int addressId
) {
}
