package com.turkcell.crm.customer_service.business.dtos.requests.addresses;

import com.turkcell.crm.customer_service.entities.concretes.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAddressRequest(
        @NotNull
        int cityId,
        @NotNull
        int customerId,
        @NotNull
        @Size(min = 1)
        String street,
        @NotNull
        @Size(min = 1)
        String houseFlatNumber,
        @NotNull
        @Size(min = 1)
        String description
) {
}
