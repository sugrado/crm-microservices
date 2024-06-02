package com.turkcell.crm.invoice_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.invoice_service.business.constants.Messages;
import com.turkcell.crm.invoice_service.core.business.abstracts.MessageService;
import com.turkcell.crm.invoice_service.entities.concretes.IndividualInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualInvoiceBusinessRules {
    private final MessageService messageService;

    public void individualInvoiceShouldBeExists(IndividualInvoice individualInvoiceOptional) {
        if (individualInvoiceOptional == null) {
            throw new NotFoundException(messageService.getMessage(Messages.IndividualInvoiceMessages.INDIVIDUAL_INVOICE_NOT_FOUND));
        }
    }
}
