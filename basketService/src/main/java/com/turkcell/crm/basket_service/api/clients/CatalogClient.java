package com.turkcell.crm.basket_service.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogService", path = "catalog-service/api/v1")
public interface CatalogClient {
    @GetMapping("/products/{id}")
    void checkIfProductExist(@PathVariable int id);
}
