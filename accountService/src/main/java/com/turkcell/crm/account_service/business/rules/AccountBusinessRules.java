package com.turkcell.crm.account_service.business.rules;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.constants.Messages;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountBusinessRules {
    private final MessageService messageService;
    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;

    public void accountShouldBeExist(Optional<Account> account) {
        if (account.isEmpty()) {
            throw new BusinessException(this.messageService.getMessage(Messages.AccountMessages.NOT_FOUND));
        }
    }

    public void accountShouldBeExist(int id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        accountShouldBeExist(accountOptional);
    }

    public void customerShouldBeExists(int customerId) {
        customerClient.checkIfCustomerExists(customerId);
    }

    public void accountShouldBeNotDeleted(Optional<Account> account) {
        if (account.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.AccountMessages.DELETED));
        }
    }


}
