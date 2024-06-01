package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.DistrictService;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/districts")
public class DistrictsController {
    private final DistrictService districtService;

    @GetMapping
    public List<GetAllDistrictsResponse> getAll() {
        return this.districtService.getAll();
    }
}
