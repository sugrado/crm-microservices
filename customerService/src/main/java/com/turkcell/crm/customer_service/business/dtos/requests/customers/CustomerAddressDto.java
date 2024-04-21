package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerAddressDto (
    @NotNull
    int customerId,
    @NotNull
    @Size(min = 1)
    String city,
    @NotNull
    @Size(min = 1)
    String street,
    @NotNull
    @Size(min = 1)
    String houseFlatNumber,
    @NotNull
    @Size(min = 1)
    String description
){

}
