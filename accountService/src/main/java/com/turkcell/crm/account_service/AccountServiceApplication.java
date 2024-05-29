package com.turkcell.crm.account_service;

import com.turkcell.crm.common.annotations.EnableFeignConfig;
import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.Common.SharedPackage, Paths.Account.BasePackage})
@EnableFeignConfig
@EnableSecurity
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
