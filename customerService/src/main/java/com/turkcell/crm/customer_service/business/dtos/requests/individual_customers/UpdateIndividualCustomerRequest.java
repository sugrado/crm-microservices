package com.turkcell.crm.customer_service.business.dtos.requests.individual_customers;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateIndividualCustomerRequest(
        @Size(min = 1)
        String firstName,
        @Size(min = 1)
        String middleName,
        @Size(min = 1)
        String lastName,
        LocalDate birthDate,
        @Size(min = 1)
        String motherName,
        @Size(min = 1)
        String fatherName,
        Gender gender,
        @Valid
        UpdateCustomerRequest customer
) {
}
