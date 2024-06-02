package com.turkcell.crm.basket_service.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", path = "account-service/api/v1")
public interface AccountClient {
    @GetMapping("/accounts/{id}")
    void checkIfAccountExist(@PathVariable int id);
}
