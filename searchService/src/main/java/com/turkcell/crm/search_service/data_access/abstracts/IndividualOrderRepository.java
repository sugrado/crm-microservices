package com.turkcell.crm.search_service.data_access.abstracts;

import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndividualOrderRepository extends MongoRepository<IndividualOrder, Integer> {
}
