package com.turkcell.crm.searchService.business.mappers;

import com.turkcell.crm.common.events.CustomerCreatedEvent;
import com.turkcell.crm.searchService.core.utilities.mapping.MapstructService;
import com.turkcell.crm.searchService.entities.concretes.Customer;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    Customer toCustomer(CustomerCreatedEvent customerCreatedEvent);
}
