package com.turkcell.crm.search_service.core.services.search;

import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;

import java.util.List;

public interface SearchService {
    <T> List<T> dynamicSearch(DynamicQuery dynamicQuery, Class<T> type);
}
