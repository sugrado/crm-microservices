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
}