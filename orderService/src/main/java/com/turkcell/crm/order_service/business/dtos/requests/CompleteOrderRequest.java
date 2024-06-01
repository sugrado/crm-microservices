package com.turkcell.crm.order_service.business.dtos.requests;

public record CompleteOrderRequest(
        int accountId,
        int addressId
) {
}
