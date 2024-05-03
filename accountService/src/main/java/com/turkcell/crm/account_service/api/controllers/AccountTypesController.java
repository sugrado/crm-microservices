package com.turkcell.crm.account_service.api.controllers;

import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/account-types")
public class AccountTypesController {
    private AccountTypeService accountTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountTypeResponse add(@Valid @RequestBody CreateAccountTypeRequest accountType) {
        return this.accountTypeService.add(accountType);
    }
}
