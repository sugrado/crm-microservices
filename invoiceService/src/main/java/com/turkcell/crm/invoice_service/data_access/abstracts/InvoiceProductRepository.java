package com.turkcell.crm.invoice_service.data_access.abstracts;

import com.turkcell.crm.invoice_service.entities.concretes.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, UUID> {
    List<InvoiceProduct> findByInvoiceId(UUID invoiceId);
}
