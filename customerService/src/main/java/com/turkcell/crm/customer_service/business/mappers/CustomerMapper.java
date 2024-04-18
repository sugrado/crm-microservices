package com.turkcell.crm.customer_service.business.mappers;


import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    CreatedCustomerResponse toCreatedCustomerResponse(Customer customer);

    List<GetAllCustomerResponse> toGetAllCustomerResponseList(List<Customer> customerList);

    GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer);

    DeletedCustomerResponse toDeletedCustomerResponse(Customer customer);

    Customer toCustomer(UpdateCustomerRequest updateCustomerRequest);

    UpdatedCustomerResponse toUpdatedCustomerResponse(Customer customer);

    @Mapping(ignore = true, target = "createdDate")
    void updateCustomerFromRequest(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
}