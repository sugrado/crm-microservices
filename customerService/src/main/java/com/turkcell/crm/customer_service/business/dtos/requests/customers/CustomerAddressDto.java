package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerAddressDto {
    @NotNull
    @Size(min = 1)
    private String city;
    @NotNull
    @Size(min = 1)
    private String street;
    @NotNull
    @Size(min = 1)
    private String houseFlatNumber;
    @NotNull
    @Size(min = 1)
    private String description;
}
