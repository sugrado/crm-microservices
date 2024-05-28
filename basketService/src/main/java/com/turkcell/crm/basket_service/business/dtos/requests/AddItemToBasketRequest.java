package com.turkcell.crm.basket_service.business.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record AddItemToBasketRequest(
        @NotNull
        String customerId,
        @NotNull
        String productId
) {

}
