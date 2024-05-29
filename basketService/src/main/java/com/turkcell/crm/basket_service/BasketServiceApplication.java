package com.turkcell.crm.basket_service;

import com.turkcell.crm.common.annotations.EnableFeignConfig;
import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.Common.SharedPackage, Paths.Basket.BasePackage})
@EnableFeignConfig
@EnableSecurity
public class BasketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasketServiceApplication.class, args);
    }

}
