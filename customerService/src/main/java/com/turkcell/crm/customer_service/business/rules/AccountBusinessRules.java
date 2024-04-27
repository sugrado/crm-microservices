package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.customer_service.business.constants.messages.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBusinessRules {
    private final MessageService messageService;
    private final AccountRepository accountRepository;

    public void accountShouldBeExist(int id) {
        boolean exists = this.accountRepository.existsById(id);
        if (!exists) {
            throw new BusinessException(this.messageService.getMessage(Messages.AccountMessages.NOT_FOUND));
        }
    }
}
