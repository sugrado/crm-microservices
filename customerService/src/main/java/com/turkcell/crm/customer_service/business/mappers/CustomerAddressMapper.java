package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.CustomerAddressDto;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.CustomerAddress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CustomerAddressMapper {
    CustomerAddress toCustomerAddress(CustomerAddressDto customerAddressDto);
}
