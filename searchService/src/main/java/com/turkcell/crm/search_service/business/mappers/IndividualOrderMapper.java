package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface IndividualOrderMapper {
    IndividualOrder toOrder(IndividualOrderCreatedEvent individualOrderCreatedEvent);
}
