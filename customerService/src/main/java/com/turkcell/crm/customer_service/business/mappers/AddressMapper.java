package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.requests.customers.AddressDto;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.Address;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AddressMapper {
    Address toCustomerAddress(AddressDto addressDto);
}
