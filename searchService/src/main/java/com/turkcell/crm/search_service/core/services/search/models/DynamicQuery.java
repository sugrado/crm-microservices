package com.turkcell.crm.search_service.core.services.search.models;

import java.util.List;

public record DynamicQuery(
        List<DynamicFilter> filters,
        List<DynamicSort> sorts
) {
}
