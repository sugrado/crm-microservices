package com.turkcell.crm.account_service.business.rules;

import com.turkcell.crm.account_service.business.constants.Messages;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountTypeRepository;
import com.turkcell.crm.account_service.entities.concretes.AccountType;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTypeBusinessRules {
    private final MessageService messageService;
    private final AccountTypeRepository accountTypeRepository;

    public void accountTypeShouldBeExist(Optional<AccountType> accountType) {
        if (accountType.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.AccountTypeMessages.NOT_FOUND));
        }
    }

    public void accountTypeNameCannotBeDuplicatedWhenInserted(String name) {
        Optional<AccountType> optionalAccountType = this.accountTypeRepository.findByName(name);
        if (optionalAccountType.isPresent()) {
            throw new BusinessException(Messages.AccountTypeMessages.ALREADY_EXISTS);
        }
    }

    public void accountTypeShouldBeNotDeleted(Optional<AccountType> accountType) {
        if (accountType.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.AccountTypeMessages.DELETED));
        }
    }
}