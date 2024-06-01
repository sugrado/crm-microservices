package com.turkcell.crm.invoice_service;

import com.turkcell.crm.common.annotations.EnableFeignConfig;
import com.turkcell.crm.common.utils.constants.Paths;
import com.turkcell.crm.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.Invoice.BasePackage, Paths.Common.SharedPackage})
@EnableFeignConfig
@EnableSecurity
public class InvoiceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);
    }
}
