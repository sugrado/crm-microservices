package com.turkcell.crm.order_service.business.dtos.responses;

public record GetAllOrdersResponse(
        int accountAddressId,
        int accountId,
        double totalPrice
) {
}
