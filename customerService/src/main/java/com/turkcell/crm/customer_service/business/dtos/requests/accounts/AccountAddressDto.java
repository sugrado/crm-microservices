package com.turkcell.crm.customer_service.business.dtos.requests.accounts;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountAddressDto {
    @NotNull
    int addressId;

    @NotNull
    int accountId;
}
