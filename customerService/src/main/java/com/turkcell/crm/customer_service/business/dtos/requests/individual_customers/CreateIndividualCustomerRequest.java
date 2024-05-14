package com.turkcell.crm.customer_service.business.dtos.requests.individual_customers;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateIndividualCustomerRequest(
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
        @Pattern(regexp = "^[1-9]\\d{9}[02468]$")
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
        CreateCustomerRequest customer
) {
}