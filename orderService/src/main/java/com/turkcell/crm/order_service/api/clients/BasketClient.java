package com.turkcell.crm.order_service.api.clients;

import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "basket-service", path = "basket-service/api/v1")
public interface BasketClient {
    @GetMapping("/baskets/{id}/products")
    List<GetProductsFromBasketDto> getProductsFromBasket(@PathVariable String id);
}
