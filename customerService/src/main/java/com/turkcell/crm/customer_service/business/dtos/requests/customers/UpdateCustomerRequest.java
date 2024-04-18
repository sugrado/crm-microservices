package com.turkcell.crm.customer_service.business.dtos.requests.customers;

import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record UpdateCustomerRequest(
        @NotNull
        @Size(min = 1)
        String firstName,
        @Size(min = 1)
        String middleName,
        @NotNull
        @Size(min = 1)
        String lastName,
        @NotNull
        LocalDate birthDate,
        @Size(min = 1)
        String motherName,
        @Size(min = 1)
        String fatherName,
        @NotNull
        Gender gender
) {
}
