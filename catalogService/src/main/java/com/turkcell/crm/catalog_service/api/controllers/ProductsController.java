package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import com.turkcell.crm.common.shared.dtos.catalogs.GetByIdProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products")
public class ProductsController {
    private final ProductService productService;

    @PostMapping
    public CreatedProductResponse add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return this.productService.add(createProductRequest);
    }

    @GetMapping("getAllByCategoryId/{categoryId}")
    public List<GetAllProductsByCategoryIdResponse> getAllByCategoryId(@PathVariable int categoryId) {
        return this.productService.getAllByCategoryId(categoryId);
    }


    @GetMapping
    public List<GetAllProductsResponse> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdProductResponse getById(@PathVariable int id) {
        return this.productService.getById(id);
    }

    @PatchMapping("{id}")
    public UpdatedProductResponse update(@PathVariable int id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return this.productService.update(id, updateProductRequest);
    }

    @DeleteMapping("{id}")
    public DeletedProductResponse delete(@PathVariable int id) {
        return this.productService.delete(id);
    }

    @PostMapping("for-complete-order")
    public List<GetAllForCompleteOrderResponse> getAllForCompleteOrder(@RequestParam List<Integer> productIdList) {
        return this.productService.getAllForCompleteOrder(productIdList);
    }
}