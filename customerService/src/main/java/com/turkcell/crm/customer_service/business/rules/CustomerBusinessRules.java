package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.customer_service.business.constants.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final MessageService messageService;
    private final CustomerRepository customerRepository;

    public void customerShouldBeExist(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.CustomerMessages.NOT_FOUND));
        }
    }

    public void customerShouldBeExist(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.CustomerMessages.NOT_FOUND));
        }
    }

    public void emailShouldBeUnique(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerMessages.EMAIL_ALREADY_EXISTS));
        }
    }
}