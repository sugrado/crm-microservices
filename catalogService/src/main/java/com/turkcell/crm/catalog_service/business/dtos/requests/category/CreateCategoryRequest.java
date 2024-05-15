package com.turkcell.crm.catalog_service.business.dtos.requests.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank
        String name,
        String description
) {
}
