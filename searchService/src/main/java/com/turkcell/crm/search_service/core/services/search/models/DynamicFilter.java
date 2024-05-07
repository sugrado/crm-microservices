package com.turkcell.crm.search_service.core.services.search.models;

import com.turkcell.crm.search_service.core.services.search.enums.FilterOperator;

public record DynamicFilter(
        String field,
        FilterOperator operator,
        String value
) {
}
