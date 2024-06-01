package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.IndividualCustomerSearchService;
import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("search-service/api/v1/customers")
public class IndividualCustomersController {
    private final IndividualCustomerSearchService customerSearchService;

    @PostMapping("/dynamic-search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchIndividualCustomersResponse> searchCustomers(@RequestBody DynamicQuery dynamicQuery) {
        return customerSearchService.searchCustomers(dynamicQuery);
    }
}
