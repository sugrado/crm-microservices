package com.turkcell.crm.search_service.core.services.search.models;

import com.turkcell.crm.search_service.core.services.search.enums.SortDirection;

public record DynamicSort(
        String field,
        SortDirection direction
) {
}
