package com.turkcell.crm.catalog_service.business.dtos.responses.product;

import com.turkcell.crm.catalog_service.business.dtos.responses.productProperty.ProductPropertyDto;

import java.time.LocalDateTime;
import java.util.List;

public record GetByIdProductResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String title,
        String description,
        double price,
        int unitsInStock,
        List<ProductPropertyDto> properties
) {
}
