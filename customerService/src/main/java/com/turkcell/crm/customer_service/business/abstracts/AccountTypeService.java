package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;

public interface AccountTypeService {
    CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountType);

    GetByIdAccountTypeResponse getById(int id);
}
