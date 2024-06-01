package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

public record GetByIdAddressResponse(
        int id,
        int customerId,
        String street,
        String houseFlatNumber,
        String description,
        boolean defaultAddress,
        int cityId,
        int districtId
) {
}
