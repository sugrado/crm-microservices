package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CreateCustomerRequest(
        @NotNull
        @Size(min = 1)
        String firstName,
        @Size(min = 0)
        String middleName,
        @NotNull
        @Size(min = 1)
        String lastName,
        @NotNull
        @Size(min = 11, max = 11)
        String nationalityId,
        @NotNull
        LocalDate birthDate,
        @Size(min = 1)
        String motherName,
        @Size(min = 1)
        String fatherName,
        @NotNull
        Gender gender,
        @Valid
        List<CustomerAddressDto> customerAddresses,
        @Valid
        CustomerContactDto customerContact
) {
}