package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.common.kafka.events.CustomerCreatedEvent;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.crm.customer_service.business.dtos.responses.individual_customers.*;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.IndividualCustomer;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface IndividualCustomerMapper {
    IndividualCustomer toIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    CreatedIndividualCustomerResponse toCreatedIndividualCustomerResponse(IndividualCustomer customer);

    List<GetAllIndividualCustomersResponse> toGetAllIndividualCustomersResponseList(List<IndividualCustomer> customerList);

    GetByIdIndividualCustomerResponse toGetByIdIndividualCustomerResponse(IndividualCustomer customer);

    DeletedIndividualCustomerResponse toDeletedIndividualCustomerResponse(IndividualCustomer customer);

    UpdatedIndividualCustomerResponse toUpdatedIndividualCustomerResponse(IndividualCustomer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIndividualCustomerFromRequest(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, @MappingTarget IndividualCustomer customer);

    @Mapping(target = "email", source = "customer.email")
    @Mapping(target = "mobilePhone", source = "customer.mobilePhone")
    @Mapping(target = "gender", source = "gender")
    CustomerCreatedEvent toCustomerCreatedEvent(IndividualCustomer customer);
}