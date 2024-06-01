package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.Customer;

import java.util.List;

public interface CustomerSearchService {
    void add(Customer customer);

    void update(Customer customer);

    void delete(int customerId);

    List<SearchCustomersResponse> searchCustomers(DynamicQuery dynamicQuery);
}
