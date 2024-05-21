package com.turkcell.crm.account_service.business.abstracts;

import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.entities.concretes.Account;

import java.util.List;

public interface AccountAddressService {
    CreatedAccountAddressResponse add(int accountId, CreateAccountAddressRequest createAccountAddressRequest);

    void add(List<AccountAddressDto> accountAddressDtoList, Account account);

    DeletedAcountAddressResponse delete(int id);

    List<GetAllByAccountIdResponse> getAllByAccountId(int accountId);
}
