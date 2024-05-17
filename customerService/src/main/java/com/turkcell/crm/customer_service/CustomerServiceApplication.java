package com.turkcell.crm.customer_service;

import com.turkcell.crm.core.annotations.EnableSecurity;
import com.turkcell.crm.core.services.SecurityService;
import com.turkcell.crm.customer_service.business.security.SecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSecurity
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    SecurityService securityService() {
        return new SecurityManager();
    }
}
