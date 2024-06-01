package com.turkcell.crm.search_service.business.dtos.responses.customers;

public record SearchIndividualCustomersResponse(
        int id,
        String firstName,
        String middleName,
        String lastName,
        String nationalityId
) {
}
