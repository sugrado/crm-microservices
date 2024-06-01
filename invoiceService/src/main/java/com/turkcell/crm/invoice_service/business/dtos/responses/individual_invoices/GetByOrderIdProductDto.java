package com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices;

public record GetByOrderIdProductDto(
        String title,
        double price
) {
}
