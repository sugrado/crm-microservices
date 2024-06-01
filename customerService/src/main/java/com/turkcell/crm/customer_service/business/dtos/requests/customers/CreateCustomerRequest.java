package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.business.constants.Regexes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreateCustomerRequest(
        @Email
        @NotNull
        String email,

        @Size(min = 11, max = 11)
        @NotNull
        @Pattern(regexp = Regexes.NUMERIC_VALIDATOR)
        String mobilePhone,

        @Valid
        @NotEmpty
        List<AddressDto> addresses
) {
}
