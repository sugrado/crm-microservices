package com.turkcell.crm.customer_service.business.dtos.responses.customers;

import com.turkcell.crm.customer_service.entities.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetByIdCustomerResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String firstName,
        String middleName,
        String lastName,
        String nationalityId,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        Gender gender
) {
}