package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerContactDto (
        @NotNull
        int customerId,
        @NotNull
        @Email
        @Size(min = 1)
        String email,
        @NotNull
        @Size(min = 13,max = 15)
        String homePhone,
        @NotNull
        @Size(min = 13,max = 15)
        String mobilePhone,
        @NotNull
        @Size(min = 5,max = 15)
        String faxNumber

){
}
