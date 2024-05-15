package com.turkcell.crm.catalog_service.api.controllers;

import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-service/api/v1/products/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public List<CreatedPropertyResponse> add(@Valid @RequestBody List<CreatePropertyRequest> createPropertyRequest){
        return this.propertyService.add(createPropertyRequest);
    }
}
