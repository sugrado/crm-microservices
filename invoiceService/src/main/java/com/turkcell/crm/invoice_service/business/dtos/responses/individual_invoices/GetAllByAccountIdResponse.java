package com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices;

import java.util.UUID;

public record GetAllByAccountIdResponse(
        UUID id,
        int orderId,
        String firstName,
        String lastName,
        String nationalityId,
        double totalPrice
) {
}
