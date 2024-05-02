package com.turkcell.crm.search_service.data_access.abstracts;

import com.turkcell.crm.search_service.entities.concretes.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {
}
