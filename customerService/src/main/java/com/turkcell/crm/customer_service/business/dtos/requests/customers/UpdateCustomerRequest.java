package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
        @Email
        String email,

        @Size(min = 13, max = 15)
        String mobilePhone
) {
}
