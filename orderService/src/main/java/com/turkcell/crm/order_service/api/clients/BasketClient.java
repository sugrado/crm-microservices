package com.turkcell.crm.order_service.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basketService", path = "basket-service/api/v1")
public interface BasketClient {
    @GetMapping("/baskets/{id}")
    void getById(@PathVariable String id);
}
