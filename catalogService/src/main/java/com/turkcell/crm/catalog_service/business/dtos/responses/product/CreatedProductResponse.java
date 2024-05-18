package com.turkcell.crm.catalog_service.business.dtos.responses.product;

import java.time.LocalDateTime;

public record CreatedProductResponse(
        int id,
        LocalDateTime createdDate,
        String title,
        String description,
        double price,
        int unitsInStock,
        int categoryId
) {
}
