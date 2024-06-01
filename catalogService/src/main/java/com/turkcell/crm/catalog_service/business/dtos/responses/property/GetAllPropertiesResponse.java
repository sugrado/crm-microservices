package com.turkcell.crm.catalog_service.business.dtos.responses.property;

public record GetAllPropertiesResponse(
        int id,
        String name,
        int categoryId
) {
}
