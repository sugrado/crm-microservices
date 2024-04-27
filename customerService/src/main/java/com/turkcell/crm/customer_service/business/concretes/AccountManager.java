package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.*;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreateAccountResponse;
import com.turkcell.crm.customer_service.business.mappers.AccountMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import com.turkcell.crm.customer_service.entities.concretes.AccountType;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerService customerService;
    private final AccountTypeService accountTypeService;
    private final AccountAddressService accountAddressService;
    private final AddressService addressService;
    @Override
    public CreateAccountResponse add(CreateAccountRequest createAccountRequest) {
        Account account = this.accountMapper.toAccount(createAccountRequest);
        Customer customer = this.customerService.getById(createAccountRequest.customerId());
        account.setCustomer(customer);
        AccountType accountType = this.accountTypeService.getById(createAccountRequest.accountTypeId());
        account.setAccountType(accountType);
        Account createdAccount = this.accountRepository.save(account);
        accountAddressService.add(createAccountRequest.accountAddresses(),account);
        CreateAccountResponse createdAccountResponse = this.accountMapper.toCreatedAccountResponse(createdAccount);
        createdAccountResponse.setAccountTypeId(createdAccount.getAccountType().getId());
        createdAccountResponse.setCustomerId(createdAccount.getCustomer().getId());
        return createdAccountResponse;
    }
}
