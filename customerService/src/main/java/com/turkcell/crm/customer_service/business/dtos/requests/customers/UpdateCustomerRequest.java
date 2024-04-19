package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateCustomerRequest(
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
        Gender gender
) {
}
