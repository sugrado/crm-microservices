package com.turkcell.crm.catalog_service.business.dtos.responses.product;

import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.ProductPropertyDto;

import java.time.LocalDateTime;
import java.util.List;

public record DeletedProductResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        LocalDateTime deletedDate,
        String title,
        String description,
        double price,
        int unitsInStock,
        List<ProductPropertyDto> properties
) {
}
