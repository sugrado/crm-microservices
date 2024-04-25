package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.AddressService;
import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapper;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CustomerMapper customerMapper;
    private final AddressService addressService;

    @Override
    public Customer add(CreateCustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerBusinessRules.emailShouldBeUnique(customer.getEmail());
        Customer createdCustomer = this.customerRepository.save(customer);
        addressService.add(request.addresses(), customer);
        return createdCustomer;
    }

    @Override
    public Customer update(int id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);
        Customer customer = customerOptional.get();

        customerMapper.updateCustomerFromRequest(updateCustomerRequest, customer);
        this.customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer getById(int id) {
        return this.customerRepository.findById(id).get();
    }
}