package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapper;
import com.turkcell.crm.customer_service.business.rules.CustomerBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerBusinessRules customerBusinessRules;

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest request) {
        Customer customer = CustomerMapper.MAPPER.toCustomer(request);
        customerBusinessRules.nationalityIdShouldBeUnique(customer.getNationalityId());
        customerBusinessRules.nationalityIdShouldBeValid(customer);

        Customer createdCustomer = this.customerRepository.save(customer);
        return CustomerMapper.MAPPER.toCreatedCustomerResponse(createdCustomer);
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        List<Customer> customerList = this.customerRepository.findAll();
        return CustomerMapper.MAPPER.toGetAllCustomerResponseList(customerList);
    }

    @Override
    public GetByIdCustomerResponse getById(int id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);
        Customer customer = customerOptional.get();
        return CustomerMapper.MAPPER.toGetByIdCustomerResponse(customer);
    }

    @Override
    public UpdatedCustomerResponse update(int id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);
        Customer customer = customerOptional.get();

        CustomerMapper.MAPPER.updateCustomerFromRequest(updateCustomerRequest, customer);
        this.customerRepository.save(customer);

        return CustomerMapper.MAPPER.toUpdatedCustomerResponse(customer);
    }

    @Override
    public DeletedCustomerResponse delete(int id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(customerOptional);
        Customer deletedCustomer = customerOptional.get();
        deletedCustomer.setDeletedDate(LocalDateTime.now());
        this.customerRepository.save(deletedCustomer);
        return CustomerMapper.MAPPER.toDeletedCustomerResponse(deletedCustomer);
    }
}