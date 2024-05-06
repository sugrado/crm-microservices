package com.turkcell.crm.customer_service.business.dtos.responses.addresses;

import java.time.LocalDateTime;

public record DeletedAddressResponse(
        int id,
        String street,
        String houseFlatNumber,
        LocalDateTime deletedDate,
        String cityName,
        String description,
        boolean isDefaultAddress) {
}
