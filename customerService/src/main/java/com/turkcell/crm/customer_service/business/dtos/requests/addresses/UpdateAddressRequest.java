package com.turkcell.crm.customer_service.business.dtos.requests.addresses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAddressRequest(
        @NotNull
        int cityId,

        @NotNull
        int districtId,

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