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
    private String accountId;
    private List<BasketItem> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public Basket(String accountId) {
        this.accountId = accountId;
        this.items = new ArrayList<>();
    }
}
