package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.constants.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.AddressRepository;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressBusinessRules {
    private final MessageService messageService;
    private final AddressRepository addressRepository;

    public void addressShouldBeExist(int id) {
        boolean exists = this.addressRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(this.messageService.getMessage(Messages.AddressMessages.NOT_FOUND));
        }
    }

    public void addressShouldBeExist(Optional<Address> address) {
        if (address.isEmpty()) {
            throw new NotFoundException(this.messageService.getMessage(Messages.AddressMessages.NOT_FOUND));
        }
    }

    public void defaultAddressCanNotDelete(Address address) {
        if (address.isDefaultAddress()) {
            throw new BusinessException(this.messageService.getMessage(Messages.AddressMessages.DEFAULT_ADDRESS_CAN_NOT_DELETE));
        }
    }

    public void addressAndCustomerShouldBeMatch(int addressId, int customerId) {
        boolean exists = addressRepository.existsByIdAndCustomerId(addressId, customerId);
        if (!exists) {
            throw new BusinessException(this.messageService.getMessage(Messages.AddressMessages.CUSTOMER_ADDRESS_MISMATCH));
        }
    }

    public void customerShouldHaveAtLeastOneAddress(List<Address> addressList) {
        if (addressList.isEmpty()) {
            throw new BusinessException(this.messageService.getMessage(Messages.AddressMessages.CUSTOMER_SHOULD_HAVE_AT_LEAST_ONE_ADDRESS));
        }
    }
}