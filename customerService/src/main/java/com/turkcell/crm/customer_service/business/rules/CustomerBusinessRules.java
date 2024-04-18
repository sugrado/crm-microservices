package com.turkcell.crm.customer_service.business.rules;

import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityDTO;
import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityService;
import com.turkcell.crm.customer_service.business.constants.messages.CustomerMessages;
import com.turkcell.crm.customer_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private CheckNationalityService checkNationalityService;

    public void customerShouldBeExist(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new BusinessException(CustomerMessages.CUSTOMER_NOT_FOUND);
        }
    }

    public void nationalityIdShouldBeUnique(String nationalityId) {
        Optional<Customer> customer = customerRepository.findByNationalityId(nationalityId);
        if (customer.isPresent()) {
            throw new BusinessException(CustomerMessages.CUSTOMER_NATIONALITY_ID_ALREADY_EXISTS);
        }
    }

    public void nationalityIdShouldBeValid(Customer customer) {
        boolean result = checkNationalityService.validate(new CheckNationalityDTO(customer.getNationalityId(),
                customer.getFirstName(), customer.getLastName(), customer.getBirthDate().getYear()));
        if (!result) {
            throw new BusinessException(CustomerMessages.NATIONALITY_ID_NOT_VALID);
        }
    }
}