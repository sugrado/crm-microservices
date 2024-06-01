package com.turkcell.crm.search_service.data_access.abstracts;

import com.turkcell.crm.search_service.entities.concretes.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Integer> {
}
