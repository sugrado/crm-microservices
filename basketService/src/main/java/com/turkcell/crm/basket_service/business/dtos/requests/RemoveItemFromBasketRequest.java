package com.turkcell.crm.basket_service.business.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record RemoveItemFromBasketRequest(
        @NotNull
        String customerId,
        @NotNull
        String productId
) {

}
