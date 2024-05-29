package com.turkcell.crm.basket_service.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerService", path = "customer-service/api/v1")
public interface CustomerClient {
    @GetMapping("/customers/check-if-customer-exists/{customerId}")
    void checkIfCustomerExists(@PathVariable int customerId);
}
