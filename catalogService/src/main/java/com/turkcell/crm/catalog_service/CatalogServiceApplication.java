package com.turkcell.crm.catalog_service;

import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import com.turkcell.crm.core.services.SecurityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {Paths.CommonBasePackage, Paths.Catalog.BasePackage})
@EnableSecurity
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

    @Bean
    SecurityService securityService() {
        return new com.turkcell.crm.catalog_service.business.security.SecurityManager();
    }
}
