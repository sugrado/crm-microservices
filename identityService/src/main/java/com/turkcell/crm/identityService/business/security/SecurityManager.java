package com.turkcell.crm.identityService.business.security;

import com.turkcell.crm.identityService.business.constants.Roles;
import com.turkcell.crm.identityService.core.services.SecurityService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityManager implements SecurityService {
    private static final String[] WHITE_LIST_URLS = {
            "/identity-service/swagger-ui/**",
            "/identity-service/v2/api-docs",
            "/identity-service/v3/api-docs",
            "/identity-service/v3/api-docs/**",
            "/identity-service/api/v1/auth/login",
            "/identity-service/api/v1/auth/register",
            "/identity-service/api/v1/auth/refresh",
    };

    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(x -> x
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "/identity-service/api/v1/users").hasAnyAuthority(Roles.ADMIN)
                .anyRequest().authenticated());
        return http;
    }
}
