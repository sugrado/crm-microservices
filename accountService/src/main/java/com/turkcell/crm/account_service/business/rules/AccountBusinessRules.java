package com.turkcell.crm.account_service.business.rules;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.constants.Messages;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBusinessRules {
    private final MessageService messageService;
    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;

    public void accountShouldBeExist(int id) {
        boolean exists = this.accountRepository.existsById(id);
        if (!exists) {
            throw new BusinessException(this.messageService.getMessage(Messages.AccountMessages.NOT_FOUND));
        }
    }

    public void customerShouldBeExistsWhenAccountIsCreated(int customerId) {
        customerClient.checkIfCustomerExists(customerId);
    }

    public void addressShouldBeExist(int addressId) {
        this.customerClient.checkIfAddressExists(addressId);
    }
}
