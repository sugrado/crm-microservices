package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @PostMapping
    public CreatedCategoryResponse add(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return this.categoryService.add(createCategoryRequest);
    }

    @GetMapping
    public List<GetAllCategoriesResponse> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdCategoryResponse getById(@PathVariable int id) {
        return this.categoryService.getById(id);
    }

    @PatchMapping("{id}")
    public UpdatedCategoryResponse update(@PathVariable int id, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return this.categoryService.update(id, updateCategoryRequest);
    }

    @DeleteMapping("{id}")
    public DeletedCategoryResponse delete(@PathVariable int id) {
        return this.categoryService.delete(id);
    }
}
