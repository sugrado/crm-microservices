package com.turkcell.crm.catalog_service.business.dtos.requests.property;

import java.util.List;

public record CreatePropertyRequest(
        String name,
        int categoryId
) {
}
