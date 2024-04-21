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
public class CustomerContactDto {
    @NotNull
    @Email
    @Size(min = 1)
    private String email;
    @NotNull
    @Size(min = 13, max = 15)
    private String homePhone;
    @NotNull
    @Size(min = 13, max = 15)
    private String mobilePhone;
    @NotNull
    @Size(min = 5, max = 15)
    private String faxNumber;

}

