package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponse add(CreateCustomerRequest request);

    List<GetAllCustomersResponse> getAll();

    GetByIdCustomerResponse getById(int id);

    UpdatedCustomerResponse update(int id, UpdateCustomerRequest updateCustomerRequest);

    DeletedCustomerResponse delete(int id);

}