package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.CustomerUpdatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.entities.concretes.Customer;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    Customer toCustomer(CustomerCreatedEvent customerCreatedEvent);

    Customer toCustomer(CustomerUpdatedEvent customerUpdatedEvent);
}
