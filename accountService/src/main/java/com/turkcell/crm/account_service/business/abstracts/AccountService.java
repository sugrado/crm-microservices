package com.turkcell.crm.account_service.business.abstracts;

import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.CreatedAccountResponse;

public interface AccountService {
    CreatedAccountResponse add(CreateAccountRequest createAccountRequest);
}
