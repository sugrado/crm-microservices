package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreateAccountResponse;

public interface AccountService {
    CreateAccountResponse add(CreateAccountRequest createAccountRequest);
}
