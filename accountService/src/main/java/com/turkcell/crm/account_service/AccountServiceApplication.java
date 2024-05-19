package com.turkcell.crm.account_service;

import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import com.turkcell.crm.core.services.SecurityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {Paths.CommonBasePackage, Paths.Account.BasePackage})
@EnableFeignClients
@EnableSecurity
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    SecurityService securityService() {
        return new com.turkcell.crm.account_service.business.security.SecurityManager();
    }
}
