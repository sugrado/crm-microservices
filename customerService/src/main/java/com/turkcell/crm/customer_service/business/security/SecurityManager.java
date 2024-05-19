package com.turkcell.crm.customer_service.business.security;

import com.turkcell.crm.core.services.SecurityService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityManager implements SecurityService {
    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(x -> x
                .requestMatchers(HttpMethod.POST, "/customer-service/api/v1/addresses").hasAuthority("admin")
                .anyRequest().authenticated());
        return http;
    }
}
