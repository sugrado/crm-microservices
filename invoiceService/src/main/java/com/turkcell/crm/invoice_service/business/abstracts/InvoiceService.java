package com.turkcell.crm.invoice_service.business.abstracts;

import com.turkcell.crm.invoice_service.entities.concretes.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice create(Invoice invoice);

    List<Invoice> getAllByAccountId(int accountId);

    Invoice getByOrderId(int orderId);
}
