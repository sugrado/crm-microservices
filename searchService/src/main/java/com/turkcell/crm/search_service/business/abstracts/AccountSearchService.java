package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.Account;

import java.util.List;

public interface AccountSearchService {

    void add(Account account);

    void update(Account account);

    void delete(int id);

    List<Account> searchAccounts(DynamicQuery dynamicQuery);
}
