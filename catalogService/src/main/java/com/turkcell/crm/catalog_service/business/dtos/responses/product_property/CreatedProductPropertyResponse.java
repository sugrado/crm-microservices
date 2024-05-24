package com.turkcell.crm.catalog_service.business.dtos.responses.product_property;

import java.time.LocalDateTime;

public record CreatedProductPropertyResponse(
        int id,
        int productId,
        int propertyId,
        LocalDateTime createdDate,
        String value
) {
}
