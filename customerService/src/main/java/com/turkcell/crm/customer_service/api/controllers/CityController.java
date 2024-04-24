package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetByIdCityResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/cities")
public class CityController {
    private final CityService cityService;
    @GetMapping
    public List<GetAllCitiesResponse> getAll(){ return this.cityService.getAll();}
}
