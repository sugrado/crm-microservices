package com.turkcell.crm.invoice_service.business.abstracts;

import com.turkcell.crm.common.shared.kafka.events.orders.OrderCreatedEventProduct;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import com.turkcell.crm.invoice_service.entities.concretes.InvoiceProduct;

import java.util.List;

public interface InvoiceProductService {
    List<InvoiceProduct> addToInvoice(Invoice invoice, List<OrderCreatedEventProduct> products);
}
