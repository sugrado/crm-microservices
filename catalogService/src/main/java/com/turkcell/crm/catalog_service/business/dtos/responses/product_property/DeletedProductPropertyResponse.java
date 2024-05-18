package com.turkcell.crm.catalog_service.business.dtos.responses.product_property;

public record DeletedProductPropertyResponse(
        int id,
        int productId,
        int propertyId
) {
}
