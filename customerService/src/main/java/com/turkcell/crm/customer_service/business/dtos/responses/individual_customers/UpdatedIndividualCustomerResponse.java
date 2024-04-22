package com.turkcell.crm.customer_service.business.dtos.responses.individual_customers;

import com.turkcell.crm.customer_service.business.dtos.responses.customers.CustomerDto;
import com.turkcell.crm.customer_service.entities.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdatedIndividualCustomerResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String firstName,
        String middleName,
        String lastName,
        String homePhone,
        String nationalityId,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        Gender gender,
        CustomerDto customer
) {
}