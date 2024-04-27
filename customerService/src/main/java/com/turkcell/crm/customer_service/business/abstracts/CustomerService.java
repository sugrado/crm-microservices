package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.entities.concretes.Customer;

public interface CustomerService {
    Customer add(CreateCustomerRequest request);

    Customer update(int id, UpdateCustomerRequest updateCustomerRequest);
}