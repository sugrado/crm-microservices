package com.turkcell.crm.invoice_service.data_access.abstracts;

import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findAllByAccountId(int accountId);

    Optional<Invoice> findByOrderId(int orderId);
}
