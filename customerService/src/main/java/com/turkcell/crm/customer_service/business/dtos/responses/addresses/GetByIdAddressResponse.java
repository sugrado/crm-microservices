package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

public record GetByIdAddressResponse(
        String street,
        String houseFlatNumber,
        String description
) {
}
