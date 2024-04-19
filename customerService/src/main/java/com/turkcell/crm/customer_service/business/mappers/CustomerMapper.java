package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.customers.*;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    CreatedCustomerResponse toCreatedCustomerResponse(Customer customer);

    List<GetAllCustomersResponse> toGetAllCustomersResponseList(List<Customer> customerList);

    GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer);

    DeletedCustomerResponse toDeletedCustomerResponse(Customer customer);

    UpdatedCustomerResponse toUpdatedCustomerResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromRequest(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
}