package com.turkcell.crm.account_service.api.controllers;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("account-service/api/v1/accounts/addresses")//Pathten accoun ıd kaldırıldı çünkü alttada olduğu için kızıyordu
public class AccountAddressesController {
    private final AccountAddressService accountAddressService;

    @PostMapping("{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountAddressResponse add(@PathVariable int accountId, @Valid @RequestBody CreateAccountAddressRequest createAccountAddressRequest) {
        return this.accountAddressService.add(accountId, createAccountAddressRequest);
    }

    @GetMapping("{accountId}")
    public List<GetAllByAccountIdResponse> getAllByAccountId(@PathVariable int accountId){
        return this.accountAddressService.getAllByAccountId(accountId);
    }

    @DeleteMapping("{id}")
    public DeletedAcountAddressResponse delete(@PathVariable int id){
        return this.accountAddressService.delete(id);
    }
}
