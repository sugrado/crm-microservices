package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.business.dtos.responses.individual_customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;

import java.util.List;

public interface IndividualCustomerSearchService {
    void add(IndividualCustomer individualCustomer);

    void update(IndividualCustomer individualCustomer);

    void delete(int individualCustomerId);

    List<SearchIndividualCustomersResponse> search(DynamicQuery dynamicQuery);
}
