package com.turkcell.crm.account_service.business.rules;

import com.turkcell.crm.account_service.api.clients.CustomerClient;
import com.turkcell.crm.account_service.business.constants.Messages;
import com.turkcell.crm.account_service.core.business.abstracts.MessageService;
import com.turkcell.crm.account_service.data_access.abstracts.AccountAddressRepository;
import com.turkcell.crm.account_service.data_access.abstracts.AccountRepository;
import com.turkcell.crm.account_service.entities.concretes.Account;
import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import com.turkcell.crm.common.shared.dtos.customers.CheckAddressAndCustomerMatchRequest;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountAddressBusinessRules {
    private final AccountAddressRepository accountAddressRepository;
    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;
    private final MessageService messageService;

    public void addressMustBelongToAccountOwner(int accountId, int addressId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new NotFoundException(Messages.AccountAddressMessages.NOT_FOUND);
        }
        customerClient.checkAddressAndCustomerMatch(new CheckAddressAndCustomerMatchRequest(account.get().getCustomerId(), addressId));
    }

    public void addressShouldNotBeExistInAccount(int accountId, int addressId) {
        boolean exists = accountAddressRepository.existsByAccountIdAndAddressId(accountId, addressId);
        if (exists) {
            throw new BusinessException(messageService.getMessage(Messages.AccountAddressMessages.ACCOUNT_ADDRESS_ALREADY_EXISTS_IN_ACCOUNT));
        }
    }


    public void addressShouldBeExist(int addressId) {
        this.customerClient.checkIfAddressExists(addressId);
    }

    public void accountAddressShouldBeExist(Optional<AccountAddress> accountAddress) {
        if (accountAddress.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.AccountAddressMessages.NOT_FOUND));
        }
    }

    public void accountAddressShouldBeNotDeleted(AccountAddress accountAddress) {
        if (accountAddress.getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.AccountAddressMessages.DELETED));
        }
    }
}
