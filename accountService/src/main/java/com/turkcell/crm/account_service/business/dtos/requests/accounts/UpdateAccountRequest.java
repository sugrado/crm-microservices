package com.turkcell.crm.account_service.business.dtos.requests.accounts;

import com.turkcell.crm.account_service.entities.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAccountRequest(
        Status status,
        @NotNull
        @Size(min = 1)
        String name,
        @NotNull
        @Size(min = 1)
        String number,
        int typeId
) {
}
