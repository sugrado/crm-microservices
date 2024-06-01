package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.CustomerSearchService;
import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("search-service/api/v1/customers")
public class CustomersController {
    private final CustomerSearchService customerSearchService;

    @PostMapping("/dynamic-search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchCustomersResponse> searchCustomers(@RequestBody DynamicQuery dynamicQuery) {
        return customerSearchService.searchCustomers(dynamicQuery);
    }
}
