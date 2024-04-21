package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityDTO;
import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityService;
import com.turkcell.crm.customer_service.business.constants.messages.Messages;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final CheckNationalityService checkNationalityService;
    private final MessageService messageService;

    public void customerShouldBeExist(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerMessages.CUSTOMER_NOT_FOUND));
        }
    }

    public void nationalityIdShouldBeUnique(String nationalityId) {
        Optional<Customer> customer = customerRepository.findByNationalityId(nationalityId);
        if (customer.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_ALREADY_EXISTS));
        }
    }

    public void nationalityIdShouldBeValid(Customer customer) {
        boolean result = checkNationalityService.validate(new CheckNationalityDTO(customer.getNationalityId(),
                customer.getFirstName() + " " + customer.getMiddleName(), customer.getLastName(), customer.getBirthDate().getYear()));
        if (!result) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerMessages.CUSTOMER_NATIONALITY_ID_NOT_VALID));
        }
    }
}