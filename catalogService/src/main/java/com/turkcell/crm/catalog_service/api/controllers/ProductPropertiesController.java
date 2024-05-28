package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.UpdateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/{productId}/properties")
public class ProductPropertiesController {
    private final ProductPropertyService productPropertyService;

    @PostMapping
    public CreatedProductPropertyResponse add(@PathVariable int productId, @Valid @RequestBody CreateProductPropertyRequest request) {
        return this.productPropertyService.add(productId, request);
    }

    @GetMapping
    public List<GetAllProductPropertiesByProductIdResponse> getAllByProductId(@PathVariable int productId) {
        return this.productPropertyService.getAllByProductId(productId);
    }

    @GetMapping("{propertyId}")
    public GetByIdProductPropertyResponse geyById(@PathVariable int productId, @PathVariable int propertyId) {
        return this.productPropertyService.getById(productId, propertyId);
    }

    @PatchMapping("{propertyId}")
    public UpdatedProductPropertyResponse update(@PathVariable int productId, @PathVariable int propertyId, @Valid @RequestBody UpdateProductPropertyRequest updateProductPropertyRequest) {
        return this.productPropertyService.update(productId, propertyId, updateProductPropertyRequest);
    }

    @DeleteMapping("{propertyId}")
    public DeletedProductPropertyResponse delete(@PathVariable int propertyId, @PathVariable int productId) {
        return this.productPropertyService.delete(productId, propertyId);
    }
}
