package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/accounts/{accountId}/addresses")
public class AccountAddressesController {
    private final AccountAddressService accountAddressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountAddressResponse add(@PathVariable int accountId, @Valid @RequestBody CreateAccountAddressRequest createAccountAddressRequest) {
        return this.accountAddressService.add(accountId, createAccountAddressRequest);
    }
}
