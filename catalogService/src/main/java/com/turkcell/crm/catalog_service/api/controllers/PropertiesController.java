package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/properties")
public class PropertiesController {

    private final PropertyService propertyService;

    @GetMapping("getAllByCategoryId/{categoryId}")
    public List<GetAllPropertiesByCategoryIdResponse> getAllByCategoryId(@PathVariable int categoryId) {
        return this.propertyService.getAllByCategoryId(categoryId);
    }

    @GetMapping
    public List<GetAllPropertiesResponse> getAll() {
        return this.propertyService.getAll();
    }

    @PostMapping
    public CreatedPropertyResponse add(@Valid @RequestBody CreatePropertyRequest createPropertyRequest) {
        return this.propertyService.add(createPropertyRequest);
    }

    @GetMapping("{id}")
    public GetByIdPropertyResponse getById(@PathVariable int id) {
        return this.propertyService.getById(id);
    }

    @DeleteMapping("{id}")
    public DeletePropertyResponse delete(@PathVariable int id) {
        return this.propertyService.delete(id);
    }
}
