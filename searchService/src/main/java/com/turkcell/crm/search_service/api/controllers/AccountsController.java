package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.AccountSearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.Account;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("search-service/api/v1/accounts")
public class AccountsController {
    private final AccountSearchService accountSearchService;

    @PostMapping("/dynamic-search")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> searchAccounts(@RequestBody DynamicQuery dynamicQuery) {
        return accountSearchService.search(dynamicQuery);
    }
}
