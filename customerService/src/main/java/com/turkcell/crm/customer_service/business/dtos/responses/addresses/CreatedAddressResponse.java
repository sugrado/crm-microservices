package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

public record CreatedAddressResponse(
        int id,
        String street,
        String houseFlatNumber,
        String description,
        boolean defaultAddress,
        int cityId
) {
}
