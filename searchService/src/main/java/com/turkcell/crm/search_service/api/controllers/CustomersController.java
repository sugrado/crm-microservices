package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.CustomerSearchService;
import com.turkcell.crm.search_service.business.dtos.requests.FilterRequest;
import com.turkcell.crm.search_service.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("search-service/api/v1/customers")
public class CustomersController {
    private final CustomerSearchService customerSearchService;

    @PostMapping("/dynamic-search")
    public List<Customer> searchCustomers(@RequestBody FilterRequest filterRequest) {
        return customerSearchService.searchCustomers(filterRequest.searchParams());
    }
}
