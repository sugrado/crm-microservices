package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.customer_service.entities.concretes.AccountType;

import java.util.List;

public interface AccountTypeService {
    CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountType);
    AccountType getById(int id);
    List<GetAllAccountTypeResponse> getAll();

}
