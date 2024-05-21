package com.turkcell.crm.account_service.business.abstracts;

import com.turkcell.crm.account_service.business.dtos.requests.account_types.CreateAccountTypeRequest;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.CreatedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.DeletedAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetAllAccountTypeResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_types.GetByIdAccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountType);

    GetByIdAccountTypeResponse getById(int id);

    DeletedAccountTypeResponse delete(int id);

    List<GetAllAccountTypeResponse> getAll();
}
