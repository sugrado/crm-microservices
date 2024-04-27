package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.customer_service.business.abstracts.AccountService;
import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import com.turkcell.crm.customer_service.entities.concretes.AccountAddress;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountAddressManager implements AccountAddressService {

    private final AccountAddressRepository accountAddressRepository;
    private final AccountService accountService;
    private final AccountAddressMapper accountAddressMapper;
    private final AddressService addressService;

    @Override
    public void add(List<AccountAddressDto> accountAddressDtoList, Account account) {
        List<AccountAddress> addressList = accountAddressDtoList.stream().map(x-> {
            AccountAddress accountAddress = accountAddressMapper.toAccountAddress(x);
            accountAddress.setAccount(account);
            Address address = this.addressService.getById(x.getAddressId());
            accountAddress.setAddress(address);
            return accountAddress;
        }).toList();

        accountAddressRepository.saveAll(addressList);
    }
}
