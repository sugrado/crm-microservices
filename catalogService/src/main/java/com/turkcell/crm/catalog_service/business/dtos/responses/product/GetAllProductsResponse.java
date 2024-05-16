package com.turkcell.crm.catalog_service.business.dtos.responses.product;

import com.turkcell.crm.catalog_service.business.dtos.responses.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import com.turkcell.crm.catalog_service.entities.concretes.Property;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record GetAllProductsResponse(
        int id,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        String title,
        String description,
        double price,
        int unitsInStock,
        int categoryId
) {
}
