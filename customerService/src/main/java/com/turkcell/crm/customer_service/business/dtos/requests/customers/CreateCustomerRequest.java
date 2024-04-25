package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateCustomerRequest(
        @Email
        @NotNull
        String email,
        @NotNull
        @Size(min = 13, max = 15)
        String mobilePhone,
        @Valid
        List<AddressDto> addresses
) {
}
