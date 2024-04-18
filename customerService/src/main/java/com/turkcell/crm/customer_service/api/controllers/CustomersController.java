package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomersController {
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return this.customerService.add(createCustomerRequest);
    }

    @GetMapping
    public List<GetAllCustomerResponse> getAll() {
        return this.customerService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdCustomerResponse getById(@PathVariable int id) {
        return this.customerService.getById(id);
    }

    @PutMapping("{id}")
    public UpdatedCustomerResponse update(@PathVariable int id, @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return this.customerService.update(id, updateCustomerRequest);
    }

    @DeleteMapping("{id}")
    public DeletedCustomerResponse delete(@PathVariable int id) {
        return this.customerService.delete(id);
    }
}