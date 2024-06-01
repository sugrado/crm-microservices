package com.turkcell.crm.catalog_service.business.dtos.responses.property;

import java.time.LocalDateTime;

public record UpdatedPropertyResponse(
        int id,
        int categoryId,
        String name,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
