package com.turkcell.crm.catalog_service.business.dtos.requests.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(
        @NotBlank
        String name,
        String description
) {
}
