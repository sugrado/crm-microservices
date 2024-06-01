package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

public record ChangedDefaultAddressResponse(
        int id,
        String street,
        String houseFlatNumber,
        String description,
        boolean defaultAddress,
        int cityId,
        int districtId
) {
}
