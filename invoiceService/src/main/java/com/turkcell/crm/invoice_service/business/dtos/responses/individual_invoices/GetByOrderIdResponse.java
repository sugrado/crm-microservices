package com.turkcell.crm.invoice_service.business.dtos.responses.individual_invoices;

import java.util.List;

public record GetByOrderIdResponse(
        String firstName,
        String lastName,
        String nationalityId,
        int accountId,
        int orderId,
        String email,
        String mobilePhone,
        double totalPrice,
        String address,
        List<GetByOrderIdProductDto> products
) {
}
