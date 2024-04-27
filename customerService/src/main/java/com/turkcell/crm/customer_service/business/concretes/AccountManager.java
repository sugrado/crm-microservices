package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.customer_service.business.abstracts.AccountService;
import com.turkcell.crm.customer_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.accounts.CreatedAccountResponse;
import com.turkcell.crm.customer_service.business.mappers.AccountMapper;
import com.turkcell.crm.customer_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.customer_service.entities.concretes.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountAddressService accountAddressService;
    private final CustomerBusinessRules customerBusinessRules;
    private final AccountTypeBusinessRules accountTypeBusinessRules;

    @Override
    public CreatedAccountResponse add(CreateAccountRequest createAccountRequest) {
        customerBusinessRules.customerShouldBeExist(createAccountRequest.customerId());
        accountTypeBusinessRules.accountTypeShouldBeExist(createAccountRequest.typeId());
        Account account = this.accountMapper.toAccount(createAccountRequest);
        Account createdAccount = this.accountRepository.save(account);
        if (createAccountRequest.accountAddresses() != null && !createAccountRequest.accountAddresses().isEmpty()) {
            accountAddressService.add(createAccountRequest.accountAddresses(), account);
        }
        return this.accountMapper.toCreatedAccountResponse(createdAccount);
    }
}
