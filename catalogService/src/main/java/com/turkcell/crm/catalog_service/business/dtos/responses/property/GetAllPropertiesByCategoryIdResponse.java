package com.turkcell.crm.catalog_service.business.dtos.responses.property;

public record GetAllPropertiesByCategoryIdResponse(
        String name,
        int categoryId
) {
}
