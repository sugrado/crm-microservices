package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.customer_service.entities.concretes.Account;

import java.util.List;

public interface AccountAddressService {
    CreatedAccountAddressResponse add(int accountId, CreateAccountAddressRequest createAccountAddressRequest);

    void add(List<AccountAddressDto> accountAddressDtoList, Account account);
}
