package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.entities.concretes.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerSearchService {
    void add(Customer customer);

    void update(Customer customer);

    void delete(int customerId);

    List<Customer> searchCustomers(Map<String, String> searchParams);
}
