package com.turkcell.crm.customer_service.business.mappers;


import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
    CreatedCustomerResponse toCreatedCustomerResponse(Customer customer);
    List<GetAllCustomerResponse> toGetAllCustomerResponseList(List<Customer> customerList);
    GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer);
    DeletedCustomerResponse toDeletedCustomerResponse(Customer customer);
    Customer toCustomer(UpdateCustomerRequest updateCustomerRequest);
    UpdatedCustomerResponse toUpdatedCustomerResponse(Customer customer);
}