package com.turkcell.crm.search_service.business.security;

import com.turkcell.crm.core.services.SecurityService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityManager implements SecurityService {
    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        return http;
    }
}
