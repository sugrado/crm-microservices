package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.account_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.dtos.customers.GetValidatedCustomerAddressesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountAddressManager implements AccountAddressService {
    private final AccountAddressRepository accountAddressRepository;
    private final AccountAddressMapper accountAddressMapper;
    private final AccountAddressBusinessRules accountAddressBusinessRules;
    private final AccountBusinessRules accountBusinessRules;
    private final CustomerClient customerClient;

    @Override
    public CreatedAccountAddressResponse add(int accountId, CreateAccountAddressRequest createAccountAddressRequest) {
        accountBusinessRules.addressShouldBeExist(createAccountAddressRequest.addressId());
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
        List<Integer> validatedAddresses = customerClient
                .getValidatedCustomerAddresses(
                        new GetValidatedCustomerAddressesRequest(
                                account.getCustomerId(),
                                accountAddressDtoList
                                        .stream()
                                        .map(AccountAddressDto::addressId)
                                        .toList()
                        ))
                .stream()
                .map(GetValidatedCustomerAddressesListItemDto::addressId)
                .toList();

        List<AccountAddress> addressesToSave = accountAddressDtoList
                .stream()
                .filter(a -> validatedAddresses.contains(a.addressId()))
                .map(a -> {
                    AccountAddress accountAddress = this.accountAddressMapper.toAccountAddress(a);
                    accountAddress.setAccount(account);
                    return accountAddress;
                })
                .toList();

        accountAddressRepository.saveAll(addressesToSave);
    }
}
