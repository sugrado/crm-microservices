package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.common.kafka.events.CustomerDeletedEvent;
import com.turkcell.crm.common.kafka.events.CustomerUpdatedEvent;
import com.turkcell.crm.search_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.search_service.entities.concretes.Customer;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    Customer toCustomer(CustomerCreatedEvent customerCreatedEvent);

    Customer toCustomer(CustomerUpdatedEvent customerUpdatedEvent);
}
