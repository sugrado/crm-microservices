package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AccountService;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreateAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/accounts")
public class AccountController {
    private AccountService accountService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse add(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        return this.accountService.add(createAccountRequest);
    }
}
