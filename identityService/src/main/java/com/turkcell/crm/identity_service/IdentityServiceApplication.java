package com.turkcell.crm.identity_service;

import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.CommonBasePackage, Paths.Identity.BasePackage})
@EnableSecurity
public class IdentityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceApplication.class, args);
    }
}
