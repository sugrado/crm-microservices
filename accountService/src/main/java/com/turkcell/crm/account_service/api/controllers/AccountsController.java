package com.turkcell.crm.account_service.api.controllers;

import com.turkcell.crm.account_service.business.abstracts.AccountService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("account-service/api/v1/accounts")
public class AccountsController {
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountResponse add(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return this.accountService.add(createAccountRequest);
    }

    @GetMapping
    public List<GetAllAccountsResponse> getAll() {
        return this.accountService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdAccountResponse getById(@PathVariable int id) {
        return this.accountService.getById(id);
    }

    @GetMapping("by-customer/{customerId}")
    public List<GetAllByCustomerIdResponse> getAllByCustomerId(@PathVariable int customerId) {
        return this.accountService.getAllByCustomerId(customerId);
    }

    @PatchMapping("{id}")
    public UpdatedAccountResponse update(@PathVariable int id, @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {
        return this.accountService.update(id, updateAccountRequest);
    }

    @DeleteMapping("{id}")
    public DeleteAccountResponse delete(@PathVariable int id) {
        return this.accountService.delete(id);
    }
}
