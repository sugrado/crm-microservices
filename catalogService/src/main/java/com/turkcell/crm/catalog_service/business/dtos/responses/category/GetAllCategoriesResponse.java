package com.turkcell.crm.catalog_service.business.dtos.responses.category;

public record GetAllCategoriesResponse(

        int id,
        String name,
        String description
) {
}
