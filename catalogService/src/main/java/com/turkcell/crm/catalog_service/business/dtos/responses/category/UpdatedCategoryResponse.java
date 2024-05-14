package com.turkcell.crm.catalog_service.business.dtos.responses.category;

import java.time.LocalDateTime;

public record UpdatedCategoryResponse(
        int id,
        String name,
        String description,
        LocalDateTime updatedDate
) {
}
