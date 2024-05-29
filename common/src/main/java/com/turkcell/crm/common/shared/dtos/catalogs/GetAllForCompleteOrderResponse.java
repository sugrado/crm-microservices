package com.turkcell.crm.common.shared.dtos.catalogs;

// TODO: bunun karşılığı catalog service'te yazılacak
public record GetAllForCompleteOrderResponse(
        int id,
        String productName,
        double price
) {
}
