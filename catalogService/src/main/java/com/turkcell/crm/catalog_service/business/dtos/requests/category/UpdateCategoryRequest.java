package com.turkcell.crm.catalog_service.business.dtos.requests.category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
        @NotBlank
        String name,
        String description
) {
}
