package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapper;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CustomerMapper customerMapper;

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerBusinessRules.nationalityIdShouldBeUnique(customer.getNationalityId());
        customerBusinessRules.nationalityIdShouldBeValid(customer);

        Customer createdCustomer = this.customerRepository.save(customer);
        return customerMapper.toCreatedCustomerResponse(createdCustomer);
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        List<Customer> customerList = this.customerRepository.findAll();
        return customerMapper.toGetAllCustomerResponseList(customerList);
    }

    @Override
    public GetByIdCustomerResponse getById(int id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);

        Customer customer = customerOptional.get();
        return customerMapper.toGetByIdCustomerResponse(customer);
    }

    @Override
    public UpdatedCustomerResponse update(int id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);
        Customer customer = customerOptional.get();

        customerMapper.updateCustomerFromRequest(updateCustomerRequest, customer);
        this.customerRepository.save(customer);
        return customerMapper.toUpdatedCustomerResponse(customer);
    }

    @Override
    public DeletedCustomerResponse delete(int id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);

        Customer customerToDelete = customerOptional.get();
        customerToDelete.setDeletedDate(LocalDateTime.now());
        this.customerRepository.save(customerToDelete);
        return customerMapper.toDeletedCustomerResponse(customerToDelete);
    }
}