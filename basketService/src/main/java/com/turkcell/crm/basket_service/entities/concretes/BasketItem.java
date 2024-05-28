package com.turkcell.crm.basket_service.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BasketItem implements Serializable {
    private String id;
    private String productId;

    public BasketItem(String productId) {
        this.productId = productId;
        this.id = UUID.randomUUID().toString();
    }
}
