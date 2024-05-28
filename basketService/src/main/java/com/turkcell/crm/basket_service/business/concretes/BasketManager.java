package com.turkcell.crm.basket_service.business.concretes;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {
    private final BasketRepository basketRepository;

    @Override
    public void addItem(AddItemToBasketRequest addItemToBasketRequest) {
        // TODO: customer id feign ile customer service'ten kontrol edilecek
        // TODO: productId feign ile product service'ten kontrol edilecek
        Basket basket = this.getById(addItemToBasketRequest.customerId());
        if (basket == null) {
            basket = new Basket(addItemToBasketRequest.customerId());
        }
        basket.getItems().add(new BasketItem(addItemToBasketRequest.productId()));
        this.basketRepository.addOrUpdate(basket);
    }

    @Override
    public void removeItemFromBasket(RemoveItemFromBasketRequest removeItemFromBasketRequest) {
        // TODO: customer id feign ile customer service'ten kontrol edilecek
        // TODO: productId feign ile product service'ten kontrol edilecek
        Basket basket = this.basketRepository.getById(removeItemFromBasketRequest.customerId());
        if (basket == null) {
            return;
        }

        List<BasketItem> items = basket.getItems();
        if (items.size() < 2) {
            this.basketRepository.delete(removeItemFromBasketRequest.customerId());
            return;
        }

        Optional<BasketItem> item = items.stream()
                .filter(p -> p.getProductId().equals(removeItemFromBasketRequest.productId()))
                .findFirst();

        if (item.isEmpty()) {
            return;
        }

        items.remove(item.get());
        this.basketRepository.addOrUpdate(basket);
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
