package com.turkcell.crm.order_service.api.clients;

import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "catalogService", path = "catalog-service/api/v1")
public interface CatalogClient {
    @GetMapping("/products/for-complete-order")
    List<GetAllForCompleteOrderResponse> getAllForCompleteOrder(List<Integer> productIdList);
}
