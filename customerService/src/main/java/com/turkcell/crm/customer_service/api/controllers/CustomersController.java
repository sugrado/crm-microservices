package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/customers")
public class CustomersController {
    private final CustomerService customerService;

    @GetMapping("check-if-customer-exists/{customerId}")
    public void checkIfCustomerExists(@PathVariable int customerId) {
        customerService.checkIfCustomerExists(customerId);
    }
}
