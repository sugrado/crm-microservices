package com.turkcell.crm.catalog_service.business.dtos.requests.product;

import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CreateProductRequest(
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
        int categoryId,
        @Valid
        @NotEmpty
        List<ProductPropertyDto> properties
) {


}
