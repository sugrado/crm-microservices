package com.turkcell.crm.account_service.api.controllers;

import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.GetByIdAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("account-service/api/v1/account-types")
public class AccountTypesController {
    private AccountTypeService accountTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountTypeResponse add(@Valid @RequestBody CreateAccountTypeRequest accountType) {
        return this.accountTypeService.add(accountType);
    }

    @GetMapping
    public List<GetAllAccountTypeResponse> getAll(){
        return this.accountTypeService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdAccountTypeResponse getById(@PathVariable int id){
        return this.accountTypeService.getById(id);
    }

    @DeleteMapping("{id}")
    public DeletedAccountTypeResponse delete(@PathVariable int id){
        return this.accountTypeService.delete(id);
    }
}
