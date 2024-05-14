package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.CreateProductPropertyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/{productId}/properties")
public class ProductPropertiesController {
    private final ProductPropertyService productPropertyService;

    @PostMapping
    public  void add(@PathVariable int productId, @Valid @RequestBody CreateProductPropertyRequest request){
        productPropertyService.add(productId, request);
    }
}
