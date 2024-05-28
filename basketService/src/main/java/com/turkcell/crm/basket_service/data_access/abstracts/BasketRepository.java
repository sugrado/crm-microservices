package com.turkcell.crm.basket_service.data_access.abstracts;

import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;

import java.util.Map;

public interface BasketRepository {
    Map<String, Basket> getAll();

    Basket getById(String id);

    void addOrUpdate(Basket basket);

    void delete(String id);

    void addItemToBasket(String customerId, BasketItem basketItem);

    void removeItemFromBasket(String customerId, String productId);
}
