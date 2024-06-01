package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.IndividualCustomerUpdatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.business.dtos.responses.individual_customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface IndividualCustomerMapper {
    IndividualCustomer toCustomer(IndividualCustomerCreatedEvent individualCustomerCreatedEvent);

    IndividualCustomer toCustomer(IndividualCustomerUpdatedEvent individualCustomerUpdatedEvent);

    List<SearchIndividualCustomersResponse> toSearchIndividualCustomersResponses(List<IndividualCustomer> customers);
}
