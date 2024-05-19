package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.CreatedProductPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.DeletedProductPropertyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/{productId}/properties")
public class ProductPropertiesController {
    private final ProductPropertyService productPropertyService;

    @PostMapping
    public CreatedProductPropertyResponse add(@PathVariable int productId, @Valid @RequestBody CreateProductPropertyRequest request) {
        return this.productPropertyService.add(productId, request);
    }

    @DeleteMapping("{id}")
    public DeletedProductPropertyResponse delete(@PathVariable int id) {
        return this.productPropertyService.delete(id);
    }
}
