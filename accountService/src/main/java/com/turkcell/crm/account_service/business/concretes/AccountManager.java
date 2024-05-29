package com.turkcell.crm.account_service.business.concretes;

import com.turkcell.crm.account_service.business.abstracts.AccountAddressService;
import com.turkcell.crm.account_service.business.abstracts.AccountService;
import com.turkcell.crm.account_service.business.abstracts.AccountTypeService;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.CreateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.requests.accounts.UpdateAccountRequest;
import com.turkcell.crm.account_service.business.dtos.responses.accounts.*;
import com.turkcell.crm.account_service.business.mappers.AccountMapper;
import com.turkcell.crm.account_service.business.rules.AccountBusinessRules;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.enums.Status;
import com.turkcell.crm.account_service.kafka.producers.AccountProducer;
import com.turkcell.crm.common.shared.kafka.events.AccountDeletedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountAddressService accountAddressService;
    private final AccountBusinessRules accountBusinessRules;
    private final AccountTypeService accountTypeService;
    private final AccountProducer accountProducer;

    @Override
    @Transactional
    public CreatedAccountResponse add(CreateAccountRequest createAccountRequest) {
        this.accountBusinessRules.customerShouldBeExists(createAccountRequest.customerId());
        this.accountTypeService.getById(createAccountRequest.typeId());


        Account account = this.accountMapper.toAccount(createAccountRequest);
        Account createdAccount = this.accountRepository.save(account);
        if (createAccountRequest.accountAddresses() != null && !createAccountRequest.accountAddresses().isEmpty()) {
            accountAddressService.add(createAccountRequest.accountAddresses(), createdAccount);
        }
        return this.accountMapper.toCreatedAccountResponse(createdAccount);
    }

    @Override
    public List<GetAllAccountsResponse> getAll() {

        List<Account> accountList = this.accountRepository.findAll();

        return this.accountMapper.toGetAllAccountsResponse(accountList);
    }

    @Override
    public GetByIdAccountResponse getById(int id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);

        this.accountBusinessRules.accountShouldBeExist(accountOptional);
        this.accountBusinessRules.accountShouldBeNotDeleted(accountOptional);

        return this.accountMapper.toGetByIdAccountResponse(accountOptional.get());
    }

    @Override
    public UpdatedAccountResponse update(int id, UpdateAccountRequest updateAccountRequest) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);

        this.accountBusinessRules.accountShouldBeExist(accountOptional);
        this.accountBusinessRules.accountShouldBeNotDeleted(accountOptional);
        Account account = accountOptional.get();

        this.accountMapper.updateAccountFromRequest(updateAccountRequest, account);

        this.accountTypeService.getById(account.getType().getId());

        Account updatedAccount = this.accountRepository.save(account);

        return this.accountMapper.toUpdatedAccountResponse(updatedAccount);
    }

    @Override
    public DeleteAccountResponse delete(int id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);

        this.accountBusinessRules.accountShouldBeExist(accountOptional);
        this.accountBusinessRules.accountShouldBeNotDeleted(accountOptional);

        Account deletedAccount = accountOptional.get();
        deletedAccount.setDeletedDate(LocalDateTime.now());
        deletedAccount.setStatus(Status.PASSIVE);

        this.accountRepository.save(deletedAccount);

        AccountDeletedEvent accountDeletedEvent = new AccountDeletedEvent();
        accountDeletedEvent.setId(id);

        this.accountProducer.send(accountDeletedEvent);

        return this.accountMapper.toDeleteAccountResponse(deletedAccount);
    }

    @Override
    public List<GetAllByCustomerIdResponse> getAllByCustomerId(int customerId) {
        this.accountBusinessRules.customerShouldBeExists(customerId);

        List<Account> accountList = this.accountRepository.findAllByCustomerId(customerId);

        return this.accountMapper.toGetAllByCustomerIdResponse(accountList);
    }

}
