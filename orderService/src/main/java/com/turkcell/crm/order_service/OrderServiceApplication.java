package com.turkcell.crm.order_service;

import com.turkcell.crm.common.annotations.EnableFeignConfig;
import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.Order.BasePackage, Paths.Common.SharedPackage})
@EnableSecurity
@EnableFeignConfig
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
