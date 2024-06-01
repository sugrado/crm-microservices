package com.turkcell.crm.customer_service.business.dtos.requests.individual_customers;

import com.turkcell.crm.customer_service.annotations.validation.BirthDate;
import com.turkcell.crm.customer_service.business.constants.Regexes;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateIndividualCustomerRequest(
        @NotNull
        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String firstName,

        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String middleName,

        @NotNull
        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String lastName,

        @NotNull
        @Size(min = 11, max = 11)
        @Pattern(regexp = Regexes.TR_IDENTITY_VALIDATOR)
        String nationalityId,

        @Size(min = 11, max = 11)
        @NotNull
        @Pattern(regexp = Regexes.NUMERIC_VALIDATOR)
        String homePhone,

        @NotNull
        @BirthDate
        LocalDate birthDate,

        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String motherName,

        @Size(min = 1, max = 50)
        @Pattern(regexp = Regexes.ALPHANUMERIC_VALIDATOR)
        String fatherName,

        @NotNull
        Gender gender,

        @Valid
        CreateCustomerRequest customer
) {
}