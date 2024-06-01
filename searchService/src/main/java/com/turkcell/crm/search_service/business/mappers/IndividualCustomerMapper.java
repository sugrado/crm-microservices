package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.customers.CustomerCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.customers.CustomerUpdatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.business.dtos.responses.customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface IndividualCustomerMapper {
    IndividualCustomer toCustomer(CustomerCreatedEvent customerCreatedEvent);

    IndividualCustomer toCustomer(CustomerUpdatedEvent customerUpdatedEvent);

    SearchIndividualCustomersResponse toSearchIndividualCustomersResponse(IndividualCustomer individualCustomer);
    List<SearchIndividualCustomersResponse> toSearchIndividualCustomersResponses(List<IndividualCustomer> customers);
}
