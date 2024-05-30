package com.turkcell.crm.order_service.api.clients;

import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "catalogService", path = "catalog-service/api/v1")
public interface CatalogClient {
    @PostMapping("/products/for-complete-order")
    List<GetAllForCompleteOrderResponse> getAllForCompleteOrder(@RequestParam List<Integer> productIdList);
}
