package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.customer_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.customer_service.data_access.abstracts.AccountAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAddressBusinessRules {
    private final AccountAddressRepository accountAddressRepository;

    public void addressMustBelongToAccountOwner(int accountId, int addressId) {
        boolean match = accountAddressRepository.accountCustomerAndAddressCustomerMatch(accountId, addressId);
        if (!match) {
            throw new BusinessException("Address does not belong to account owner");
        }
    }

    public void addressShouldNotBeExistInAccount(int accountId, int addressId) {
        boolean exists = accountAddressRepository.existsByAccountIdAndAddressId(accountId, addressId);
        if (exists) {
            throw new BusinessException("Address is already exist in account");
        }
    }
}
