package com.turkcell.crm.search_service;

import com.turkcell.crm.core.annotations.EnableSecurity;
import com.turkcell.crm.core.services.SecurityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableSecurity
public class SearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

    @Bean
    SecurityService securityService() {
        return new com.turkcell.crm.search_service.business.security.SecurityManager();
    }
}
