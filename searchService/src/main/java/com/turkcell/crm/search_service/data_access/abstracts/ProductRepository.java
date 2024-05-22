package com.turkcell.crm.search_service.data_access.abstracts;

import com.turkcell.crm.search_service.entities.concretes.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Integer> {
}
