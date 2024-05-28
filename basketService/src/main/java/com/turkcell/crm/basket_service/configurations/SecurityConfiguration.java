package com.turkcell.crm.basket_service.configurations;

import com.turkcell.crm.core.configurations.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        this.baseSecurityService.securityFilterChain(http);
        return http.build();
    }
}
