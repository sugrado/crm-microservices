package com.turkcell.crm.order_service.business.dtos.responses;

public record GetByIdOrderResponse(
        int accountAddressId,
        int accountId,
        double totalPrice
) {
}
