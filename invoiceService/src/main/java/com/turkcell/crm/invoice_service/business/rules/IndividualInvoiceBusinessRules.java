package com.turkcell.crm.invoice_service.business.rules;

import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import com.turkcell.crm.invoice_service.business.constants.Messages;
import com.turkcell.crm.invoice_service.entities.concretes.IndividualInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualInvoiceBusinessRules {
    public void individualInvoiceShouldBeExists(IndividualInvoice individualInvoiceOptional) {
        if (individualInvoiceOptional == null) {
            throw new NotFoundException(Messages.IndividualInvoiceMessages.INDIVIDUAL_INVOICE_NOT_FOUND);
        }
    }
}
