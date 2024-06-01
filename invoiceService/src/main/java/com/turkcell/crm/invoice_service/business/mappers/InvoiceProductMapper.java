package com.turkcell.crm.invoice_service.business.mappers;

import com.turkcell.crm.common.shared.kafka.events.orders.OrderCreatedEventProduct;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.invoice_service.entities.concretes.InvoiceProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface InvoiceProductMapper {
    @Mapping(target = "id", ignore = true)
    InvoiceProduct toInvoiceProduct(OrderCreatedEventProduct product);

    List<InvoiceProduct> toInvoiceProducts(List<OrderCreatedEventProduct> products);
}
