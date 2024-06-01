package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.customers.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerUpdatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchCustomersResponse;
import com.turkcell.crm.search_service.entities.concretes.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CustomerMapper {
    Customer toCustomer(CustomerCreatedEvent customerCreatedEvent);

    Customer toCustomer(CustomerUpdatedEvent customerUpdatedEvent);

    List<SearchCustomersResponse> toSearchCustomersResponses(List<Customer> customers);
}
