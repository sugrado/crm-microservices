package com.turkcell.crm.search_service.business.dtos.requests;

import java.util.Map;

public record FilterRequest(
        Map<String, String> searchParams
) {
}
