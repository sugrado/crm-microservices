package com.turkcell.crm.catalog_service.business.dtos.responses.product;

import com.turkcell.crm.catalog_service.business.dtos.responses.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record CreatedProductResponse(
        int id,
        LocalDateTime createdDate,
        String title,
        String description,
        double price,
        int unitsInStock
) {
}
