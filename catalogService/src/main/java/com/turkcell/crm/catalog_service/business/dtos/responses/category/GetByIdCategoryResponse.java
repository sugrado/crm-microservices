package com.turkcell.crm.catalog_service.business.dtos.responses.category;

public record GetByIdCategoryResponse(

        int id,
        String name,
        String description
) {
}
