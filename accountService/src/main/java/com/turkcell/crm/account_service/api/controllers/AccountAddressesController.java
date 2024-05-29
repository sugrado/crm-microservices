package com.turkcell.crm.account_service.api.controllers;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("account-service/api/v1/accounts/{accountId}/addresses")
public class AccountAddressesController {
    private final AccountAddressService accountAddressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountAddressResponse add(@PathVariable int accountId, @Valid @RequestBody CreateAccountAddressRequest createAccountAddressRequest) {
        return this.accountAddressService.add(accountId, createAccountAddressRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllByAccountIdResponse> getAllByAccountId(@PathVariable int accountId) {
        return this.accountAddressService.getAllByAccountId(accountId);
    }

    @GetMapping("{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdAccountAddressResponse getById(@PathVariable int accountId, @PathVariable int addressId) {
        return this.accountAddressService.getByAccountAndAddress(accountId, addressId);
    }

    @DeleteMapping("{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedAcountAddressResponse delete(@PathVariable int accountId, @PathVariable int addressId) {
        return this.accountAddressService.delete(accountId, addressId);
    }
}
