package com.turkcell.crm.account_service.business.abstracts;

import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountResponse;

import java.util.List;

public interface AccountService {
    CreatedAccountResponse add(CreateAccountRequest createAccountRequest);

    List<GetAllAccountsResponse> getAll();

    GetByIdAccountResponse getById(int id);

    UpdatedAccountResponse update(int id, UpdateAccountRequest updateAccountRequest);

    DeleteAccountResponse delete(int id);

    List<GetAllByCustomerIdResponse> getAllByCustomerId(int customerId);


}
