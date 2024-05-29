package com.turkcell.crm.basket_service.business.abstracts;

import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;

import java.util.List;

public interface BasketService {
    void addItem(AddItemToBasketRequest addItemToBasketRequest);

    void removeItemFromBasket(RemoveItemFromBasketRequest removeItemFromBasketRequest);

    void emptyBasket(String accountId);

    Basket getById(String id);

    List<GetProductsFromBasketDto> getProductsFromBasket(String id);
}
