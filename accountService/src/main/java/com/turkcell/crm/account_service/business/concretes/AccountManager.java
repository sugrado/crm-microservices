package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.abstracts.AccountService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.CreatedAccountResponse;
import com.turkcell.crm.account_service.business.mappers.AccountMapper;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.business.rules.AccountTypeBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountAddressService accountAddressService;
    private final AccountTypeBusinessRules accountTypeBusinessRules;
    private final AccountBusinessRules accountBusinessRules;

    @Override
    public CreatedAccountResponse add(CreateAccountRequest createAccountRequest) {
        accountBusinessRules.customerShouldBeExistsWhenAccountIsCreated(createAccountRequest.customerId());
        accountTypeBusinessRules.accountTypeShouldBeExist(createAccountRequest.typeId());

        Account account = this.accountMapper.toAccount(createAccountRequest);
        Account createdAccount = this.accountRepository.save(account);
        if (createAccountRequest.accountAddresses() != null && !createAccountRequest.accountAddresses().isEmpty()) {
            accountAddressService.add(createAccountRequest.accountAddresses(), createdAccount);
        }
        return this.accountMapper.toCreatedAccountResponse(createdAccount);
    }
}
