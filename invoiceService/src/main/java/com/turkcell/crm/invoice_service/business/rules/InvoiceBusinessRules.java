package com.turkcell.crm.invoice_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.invoice_service.api.clients.AccountClient;
import com.turkcell.crm.invoice_service.business.constants.Messages;
import com.turkcell.crm.invoice_service.entities.concretes.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceBusinessRules {
    private final AccountClient accountClient;

    public void accountShouldBeExists(int accountId) {
        accountClient.getById(accountId);
    }

    public void invoiceShouldBeExists(Optional<Invoice> invoiceOptional) {
        if (invoiceOptional.isEmpty()) {
            throw new NotFoundException(Messages.InvoiceMessages.INVOICE_NOT_FOUND);
        }
    }
}
