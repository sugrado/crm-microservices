package com.turkcell.crm.catalog_service.business.dtos.requests.product_property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductPropertyRequest(
        @NotBlank
        String value,
        @NotNull
        int propertyId
) {
}
