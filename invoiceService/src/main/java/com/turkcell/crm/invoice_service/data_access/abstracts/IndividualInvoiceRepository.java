package com.turkcell.crm.invoice_service.data_access.abstracts;

import com.turkcell.crm.invoice_service.entities.concretes.IndividualInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualInvoiceRepository extends JpaRepository<IndividualInvoice, UUID> {
}
