package com.turkcell.crm.catalog_service.business.dtos.responses.product_property;

import java.time.LocalDateTime;

public record UpdatedProductPropertyResponse(
        int id,
        int productId,
        int propertyId,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String value
) {
}
