package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.CreatedCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CreatedCategoryResponse add(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
       return this.categoryService.add(createCategoryRequest);
    }
}
