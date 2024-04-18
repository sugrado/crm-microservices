package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CustomerService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.business.mappers.CustomerMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.CustomerRepository;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest request) {
        Customer customer = this.customerMapper.toCustomer(request);

        Customer createdCustomer = this.customerRepository.save(customer);

        return this.customerMapper.toCreatedCustomerResponse(createdCustomer);
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
//        List<GetAllCustomerResponse> getAllCustomerResponsesList = new ArrayList<>();
        List<Customer> customerList = this.customerRepository.findAll();
        return this.customerMapper.toGetAllCustomerResponseList(customerList);
//        for (Customer customer : customerList) {
//            GetAllCustomerResponse getAllCustomerResponse = this.modelMapperService.forResponse().map(customer, GetAllCustomerResponse.class);
//            getAllCustomerResponsesList.add(getAllCustomerResponse);
//        }
//        return getAllCustomerResponsesList;
    }

    @Override
    public GetByIdCustomerResponse getById(int id) {
        Customer customer = this.customerRepository.findById(id).get();
        return this.customerMapper.toGetByIdCustomerResponse(customer);
    }

    @Override
    public UpdatedCustomerResponse update(int id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.customerMapper.toCustomer(updateCustomerRequest);
        customer.setId(id);
        this.customerRepository.save(customer);

        return this.customerMapper.toUpdatedCustomerResponse(customer);
    }

    @Override
    public DeletedCustomerResponse delete(int id) {
        Customer deletedCustomer = this.customerRepository.findById(id).get();
        deletedCustomer.setDeletedDate(LocalDateTime.now());
        this.customerRepository.save(deletedCustomer);
        return this.customerMapper.toDeletedCustomerResponse(deletedCustomer);
    }
}