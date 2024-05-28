package com.turkcell.crm.basket_service.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Basket implements Serializable {
    private String customerId;
    private List<BasketItem> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public Basket(String customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }
}
