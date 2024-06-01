package com.turkcell.crm.search_service.data_access.abstracts;

import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndividualCustomerRepository extends MongoRepository<IndividualCustomer, Integer> {
}
