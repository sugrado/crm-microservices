package com.turkcell.crm.invoice_service.business.concretes;

import com.turkcell.crm.invoice_service.business.abstracts.InvoiceService;
import com.turkcell.crm.invoice_service.business.rules.InvoiceBusinessRules;
import com.turkcell.crm.invoice_service.data_access.abstracts.InvoiceRepository;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceBusinessRules invoiceBusinessRules;

    @Override
    public Invoice create(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllByAccountId(int accountId) {
        this.invoiceBusinessRules.accountShouldBeExists(accountId);
        return this.invoiceRepository.findAllByAccountId(accountId);
    }

    @Override
    public Invoice getByOrderId(int orderId) {
        Optional<Invoice> invoiceOptional = this.invoiceRepository.findByOrderId(orderId);
        this.invoiceBusinessRules.invoiceShouldBeExists(invoiceOptional);
        return invoiceOptional.get();
    }
}
