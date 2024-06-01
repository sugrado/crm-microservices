package com.turkcell.crm.search_service.business.concretes;

import com.turkcell.crm.search_service.business.abstracts.AccountSearchService;
import com.turkcell.crm.search_service.core.services.search.SearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.search_service.entities.concretes.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountSearchManager implements AccountSearchService {
    private final AccountRepository accountRepository;
    private final SearchService searchService;
    @Override
    public void add(Account account) {
        this.accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        this.accountRepository.save(account);
    }

    @Override
    public void delete(int id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    public List<Account> searchAccounts(DynamicQuery dynamicQuery) {
        return searchService.dynamicSearch(dynamicQuery, Account.class);
    }
}
