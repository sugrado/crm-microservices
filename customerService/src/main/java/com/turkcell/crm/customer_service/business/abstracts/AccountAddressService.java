package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import com.turkcell.crm.customer_service.entities.concretes.Address;

import java.util.List;

public interface AccountAddressService {
    void add(List<AccountAddressDto> accountAddressDtoList, Account account);
}
