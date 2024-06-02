package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.dtos.requests.account_addresses.CreateAccountAddressRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.AccountAddressDto;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.CreatedAccountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.DeletedAcountAddressResponse;
import com.turkcell.crm.account_service.business.dtos.responses.account_addresses.GetAllByAccountIdResponse;
import com.turkcell.crm.account_service.business.mappers.AccountAddressMapper;
import com.turkcell.crm.account_service.business.rules.AccountAddressBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesListItemDto;
import com.turkcell.crm.common.shared.dtos.customers.GetValidatedCustomerAddressesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountAddressManager implements AccountAddressService {
    private final AccountAddressRepository accountAddressRepository;
    private final AccountAddressMapper accountAddressMapper;
    private final AccountAddressBusinessRules accountAddressBusinessRules;
    private final CustomerClient customerClient;
    private final AccountBusinessRules accountBusinessRules;

    @Override
    public CreatedAccountAddressResponse add(int accountId, CreateAccountAddressRequest createAccountAddressRequest) {
        this.accountAddressBusinessRules.addressShouldBeExist(createAccountAddressRequest.addressId());
        this.accountBusinessRules.accountShouldBeExist(accountId);
        this.accountAddressBusinessRules.addressShouldNotBeExistInAccount(accountId, createAccountAddressRequest.addressId());
        this.accountAddressBusinessRules.addressMustBelongToAccountOwner(accountId, createAccountAddressRequest.addressId());

        AccountAddress accountAddress = this.accountAddressMapper.toAccountAddress(createAccountAddressRequest);
        accountAddress.setAccount(new Account(accountId));
        AccountAddress createdAccount = this.accountAddressRepository.save(accountAddress);
        return this.accountAddressMapper.toCreatedAccountAddressResponse(createdAccount);
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

    @Override
    public DeletedAcountAddressResponse delete(int accountId, int addressId) {
        accountBusinessRules.accountShouldBeExist(accountId);
        accountAddressBusinessRules.addressShouldBeExist(addressId);
        Optional<AccountAddress> optionalAccountAddress = this.accountAddressRepository.findByAccountIdAndAddressId(accountId, addressId);

        this.accountAddressBusinessRules.accountAddressShouldBeExist(optionalAccountAddress);
        AccountAddress accountAddressToDelete = optionalAccountAddress.get();
        this.accountAddressBusinessRules.accountAddressShouldBeNotDeleted(accountAddressToDelete);

        accountAddressToDelete.setDeletedDate(LocalDateTime.now());
        AccountAddress deletedAccountAddress = this.accountAddressRepository.save(accountAddressToDelete);

        return this.accountAddressMapper.toDeletedAcountAddressResponse(deletedAccountAddress);
    }

    @Override
    public List<GetAllByAccountIdResponse> getAllByAccountId(int accountId) {
        this.accountBusinessRules.accountShouldBeExist(accountId);

        List<AccountAddress> accountAddressList = this.accountAddressRepository.findAllAccountAddressesByAccountId(accountId);

        return this.accountAddressMapper.toGetAllByAccountIdResponse(accountAddressList);
    }

    @Override
    public GetByIdAccountAddressResponse getByAccountAndAddress(int accountId, int addressId) {

        this.accountAddressBusinessRules.addressMustBelongToAccountOwner(accountId, addressId);

        Optional<AccountAddress> accountAddress = this.accountAddressRepository.findByAccountIdAndAddressId(accountId, addressId);
        this.accountAddressBusinessRules.accountAddressShouldBeExist(accountAddress);

        return this.accountAddressMapper.toGetByIdAccountAddressResponse(accountAddress.get());
    }
}
