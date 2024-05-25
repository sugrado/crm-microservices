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

    @GetMapping("{id}")
    public GetByIdProductPropertyResponse geyById(@PathVariable int productId, @PathVariable int id) {
        return this.productPropertyService.getById(productId, id);
    }

    @PatchMapping("{id}")
    public UpdatedProductPropertyResponse update(@PathVariable int productId, @PathVariable int id, @Valid @RequestBody UpdateProductPropertyRequest updateProductPropertyRequest) {
        return this.productPropertyService.update(id, updateProductPropertyRequest);
    }

    @DeleteMapping("{id}")
    public DeletedProductPropertyResponse delete(@PathVariable int id, int productId) {
        return this.productPropertyService.delete(id);
    }
}
