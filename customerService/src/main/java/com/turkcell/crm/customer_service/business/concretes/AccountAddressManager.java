package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.customer_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.customer_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.customer_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.customer_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.customer_service.business.rules.AddressBusinessRules;
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
    private final AccountAddressMapper accountAddressMapper;
    private final AddressService addressService;
    private final AccountAddressBusinessRules accountAddressBusinessRules;
    private final AddressBusinessRules addressBusinessRules;
    private final AccountBusinessRules accountBusinessRules;

    @Override
    public CreatedAccountAddressResponse add(int accountId, CreateAccountAddressRequest createAccountAddressRequest) {
        addressBusinessRules.addressShouldBeExist(createAccountAddressRequest.addressId());
        accountBusinessRules.accountShouldBeExist(accountId);
        accountAddressBusinessRules.addressShouldNotBeExistInAccount(accountId, createAccountAddressRequest.addressId());
        accountAddressBusinessRules.addressMustBelongToAccountOwner(accountId, createAccountAddressRequest.addressId());

        AccountAddress accountAddress = accountAddressMapper.toAccountAddress(createAccountAddressRequest);
        accountAddress.setAccount(new Account(accountId));
        accountAddressRepository.save(accountAddress);
        return accountAddressMapper.toCreatedAccountAddressResponse(accountAddress);
    }

    @Override
    public void add(List<AccountAddressDto> accountAddressDtoList, Account account) {
        List<Integer> addresses = addressService
                .getAllById(accountAddressDtoList.stream().map(AccountAddressDto::addressId).toList())
                .stream()
                .filter(a -> a.getCustomer().getId().equals(account.getCustomer().getId()))
                .map(Address::getId).toList();

        List<AccountAddress> addressList = accountAddressDtoList.stream().filter(a -> addresses.contains(a.addressId())).map(x -> {
            AccountAddress accountAddress = accountAddressMapper.toAccountAddress(x);
            accountAddress.setAccount(account);
            return accountAddress;
        }).toList();

        accountAddressRepository.saveAll(addressList);
    }
}
