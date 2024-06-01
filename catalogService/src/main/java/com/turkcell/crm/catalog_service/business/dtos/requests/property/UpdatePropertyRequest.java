package com.turkcell.crm.catalog_service.business.dtos.requests.property;

public record UpdatePropertyRequest(
        String name,
        int categoryId
) {
}
