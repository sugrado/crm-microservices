package com.turkcell.crm.common.shared.dtos.catalogs;

public record GetAllForCompleteOrderResponse(
        int id,
        String title,
        double price
) {
}
