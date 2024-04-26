package com.turkcell.crm.customer_service.api.controllers;

import com.turkcell.crm.customer_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.customer_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.customer_service.entities.concretes.AccountType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer-service/api/v1/account_types")
public class AccountTypeContoller {
    private AccountTypeService accountTypeService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountTypeResponse add(@Valid @RequestBody CreateAccountTypeRequest accountType){
        return this.accountTypeService.add(accountType);
    }
    @GetMapping
    public List <GetAllAccountTypeResponse> getAll(){
        return this.accountTypeService.getAll();
    }
}
