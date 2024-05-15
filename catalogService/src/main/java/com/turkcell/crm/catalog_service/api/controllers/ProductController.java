package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.CreatedProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public CreatedProductResponse add(@Valid @RequestBody CreateProductRequest createProductRequest){
        return this.productService.add(createProductRequest);
    }
}