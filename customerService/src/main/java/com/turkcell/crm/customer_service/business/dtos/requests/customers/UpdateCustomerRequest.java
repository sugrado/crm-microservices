package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.business.constants.Regexes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
        @Email
        @NotNull
        String email,

        @Size(min = 11, max = 11)
        @NotNull
        @Pattern(regexp = Regexes.NUMERIC_VALIDATOR)
        String mobilePhone
) {
}
