package com.turkcell.crm.basket_service.business.concretes;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.business.mappers.BasketMapper;
import com.turkcell.crm.basket_service.business.rules.BasketBusinessRules;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {
    private final BasketRepository basketRepository;
    private final BasketBusinessRules basketBusinessRules;
    private final BasketMapper basketMapper;

    @Override
    public void addItem(AddItemToBasketRequest addItemToBasketRequest) {
        this.basketBusinessRules.accountShouldBeExist(addItemToBasketRequest.accountId());
        this.basketBusinessRules.productShouldBeExist(addItemToBasketRequest.productId());

        Basket basket = this.getById(addItemToBasketRequest.accountId());
        if (basket == null) {
            basket = new Basket(addItemToBasketRequest.accountId());
        }
        basket.getItems().add(new BasketItem(addItemToBasketRequest.productId()));
        this.basketRepository.addOrUpdate(basket);
    }

    @Override
    public void removeItemFromBasket(RemoveItemFromBasketRequest removeItemFromBasketRequest) {
        this.basketBusinessRules.accountShouldBeExist(removeItemFromBasketRequest.accountId());
        this.basketBusinessRules.productShouldBeExist(removeItemFromBasketRequest.productId());

        //TODO: business rule'lar eklenecek
        Basket basket = this.basketRepository.getById(removeItemFromBasketRequest.accountId());
        if (basket == null) {
            return;
        }

        List<BasketItem> items = basket.getItems();
        if (items.size() < 2) {
            this.basketRepository.delete(removeItemFromBasketRequest.accountId());
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
    public void emptyBasket(String accountId) {
        // TODO: id kontrolü yapılacak business rule
        this.basketRepository.delete(accountId);
    }

    @Override
    public Basket getById(String id) {
        // TODO: id kontrolü yapılacak business rule
        return this.basketRepository.getById(id);
    }

    @Override
    public List<GetProductsFromBasketDto> getProductsFromBasket(String id) {
        // TODO: id kontrolü yapılacak business rule
        return this.basketMapper.toGetProductsFromBasketDto(this.basketRepository.getById(id).getItems());
    }
}
