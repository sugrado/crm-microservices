package com.turkcell.crm.search_service.business.dtos.responses.customers;

public record SearchCustomersResponse(
        int id,
        String firstName,
        String middleName,
        String lastName,
        String nationalityId
) {
}
