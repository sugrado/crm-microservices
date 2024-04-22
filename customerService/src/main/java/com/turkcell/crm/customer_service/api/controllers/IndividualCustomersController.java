package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.IndividualCustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.individual_customers.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/individual-customers")
public class IndividualCustomersController {
    private IndividualCustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return this.customerService.add(createIndividualCustomerRequest);
    }

    @GetMapping
    public List<GetAllIndividualCustomersResponse> getAll() {
        return this.customerService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdIndividualCustomerResponse getById(@PathVariable int id) {
        return this.customerService.getById(id);
    }

    @PatchMapping("{id}")
    public UpdatedIndividualCustomerResponse update(@PathVariable int id, @Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        return this.customerService.update(id, updateIndividualCustomerRequest);
    }

    @DeleteMapping("{id}")
    public DeletedIndividualCustomerResponse delete(@PathVariable int id) {
        return this.customerService.delete(id);
    }
}