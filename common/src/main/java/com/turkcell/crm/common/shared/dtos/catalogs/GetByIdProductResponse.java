package com.turkcell.crm.common.shared.dtos.catalogs;

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