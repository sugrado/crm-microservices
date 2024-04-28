package com.turkcell.crm.searchService.business.concretes;

import com.turkcell.crm.searchService.business.abstracts.CustomerSearchService;
import com.turkcell.crm.searchService.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.searchService.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSearchManager implements CustomerSearchService {
    private final CustomerRepository customerRepository;

    @Override
    public void add(Customer customer) {
        this.customerRepository.save(customer);
    }
}
