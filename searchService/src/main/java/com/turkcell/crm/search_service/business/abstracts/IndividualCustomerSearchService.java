package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;

import java.util.List;

public interface IndividualCustomerSearchService {
    void add(IndividualCustomer customer);

    void update(IndividualCustomer customer);

    void delete(int customerId);

    List<SearchIndividualCustomersResponse> searchCustomers(DynamicQuery dynamicQuery);
}
