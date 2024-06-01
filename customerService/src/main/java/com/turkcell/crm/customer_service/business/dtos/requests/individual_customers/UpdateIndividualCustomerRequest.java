package com.turkcell.crm.customer_service.business.dtos.requests.individual_customers;

import com.turkcell.crm.customer_service.annotations.validation.BirthDate;
import com.turkcell.crm.customer_service.business.constants.Regexes;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateIndividualCustomerRequest(
        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String firstName,

        @Size(max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String middleName,

        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String lastName,

        @Size(min = 11, max = 11)
        @NotNull
        @Pattern(regexp = Regexes.NUMERIC_VALIDATOR)
        String homePhone,

        @BirthDate
        LocalDate birthDate,

        @Size(max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String motherName,

        @Size(max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String fatherName,

        Gender gender,

        @Valid
        UpdateCustomerRequest customer
) {
}
