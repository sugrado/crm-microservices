package com.turkcell.crm.customer_service.business.dtos.requests.account_types;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountTypeRequest(
        @NotNull
        @Size(min = 1)
        String name
) {
}
