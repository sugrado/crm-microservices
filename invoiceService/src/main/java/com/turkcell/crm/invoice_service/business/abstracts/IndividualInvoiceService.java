package com.turkcell.crm.invoice_service.business.abstracts;

import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetAllByAccountIdResponse;
import com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices.GetByOrderIdResponse;

import java.util.List;

public interface IndividualInvoiceService {
    void create(IndividualOrderCreatedEvent individualOrderCreatedEvent);

    List<GetAllByAccountIdResponse> getAllByAccountId(int accountId);

    GetByOrderIdResponse getByOrderId(int orderId);
}
