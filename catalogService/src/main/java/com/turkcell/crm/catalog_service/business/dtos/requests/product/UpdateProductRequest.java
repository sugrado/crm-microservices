package com.turkcell.crm.catalog_service.business.dtos.requests.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @NotNull
        @Size(min = 3)
        String title,
        @Size(min = 3)
        String description,
        @NotNull
        double price,
        @NotNull
        int unitsInStock,
        @NotNull
        int categoryId
) {
}
