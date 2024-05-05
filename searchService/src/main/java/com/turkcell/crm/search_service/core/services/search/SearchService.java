package com.turkcell.crm.search_service.core.services.search;

import java.util.List;
import java.util.Map;

public interface SearchService {
    <T> List<T> dynamicSearch(Map<String, String> searchParams, Class<T> type);
}
