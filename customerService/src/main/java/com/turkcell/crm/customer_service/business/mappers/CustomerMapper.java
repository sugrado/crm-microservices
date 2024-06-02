package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.customers.UpdateCustomerRequest;
import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.mapstruct.*;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    @Mapping(target = "addresses", ignore = true)
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromRequest(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
}