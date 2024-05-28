package com.turkcell.crm.basket_service.business.concretes;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {
    private final BasketRepository basketRepository;

    @Override
    public void addItem(AddItemToBasketRequest addItemToBasketRequest) {
        // TODO: customer id feign ile customer service'ten kontrol edilecek
        // TODO: productId feign ile product service'ten kontrol edilecek
        this.basketRepository.addItemToBasket(addItemToBasketRequest.customerId(), new BasketItem(addItemToBasketRequest.productId()));
    }

    @Override
    public void removeItemFromBasket(RemoveItemFromBasketRequest removeItemFromBasketRequest) {
        // TODO: customer id feign ile customer service'ten kontrol edilecek
        // TODO: productId feign ile product service'ten kontrol edilecek
        this.basketRepository.removeItemFromBasket(removeItemFromBasketRequest.customerId(), removeItemFromBasketRequest.productId());
    }

    // TODO: order created event'i gelirse sepeti boşalt
    @Override
    public void emptyBasket(String customerId) {
        this.basketRepository.delete(customerId);
    }

    @Override
    public Basket getById(String id) {
        return this.basketRepository.getById(id);
    }
}
