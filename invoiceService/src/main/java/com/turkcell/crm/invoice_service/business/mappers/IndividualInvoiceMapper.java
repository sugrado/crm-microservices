package com.turkcell.crm.invoice_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetAllByAccountIdResponse;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetByOrderIdResponse;
import com.turkcell.crm.invoice_service.entities.concretes.IndividualInvoice;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface IndividualInvoiceMapper {
    IndividualInvoice toIndividualInvoice(IndividualOrderCreatedEvent individualOrderCreatedEvent);

    @Mapping(target = "firstName", source = "individual.firstName")
    @Mapping(target = "lastName", source = "individual.lastName")
    @Mapping(target = "nationalityId", source = "individual.nationalityId")
    GetAllByAccountIdResponse toGetAllByAccountIdResponse(Invoice invoice);

    @Mapping(target = "firstName", source = "individual.firstName")
    @Mapping(target = "lastName", source = "individual.lastName")
    @Mapping(target = "nationalityId", source = "individual.nationalityId")
    GetByOrderIdResponse toGetByOrderIdResponse(Invoice invoice);
}
