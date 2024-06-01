package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

import java.time.LocalDate;

public record UpdatedAddressResponse(
        int id,
        int cityId,
        int districtId,
        int customerId,
        String street,
        String houseFlatNumber,
        String description,
        LocalDate updatedDate
) {
}