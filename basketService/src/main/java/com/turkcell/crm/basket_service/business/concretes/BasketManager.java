package com.turkcell.crm.basket_service.business.concretes;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.business.rules.BasketBusinessRules;
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
    private final BasketBusinessRules basketBusinessRules;

    @Override
    public void addItem(AddItemToBasketRequest addItemToBasketRequest) {
        // TODO: customer id feign ile account service'ten kontrol edilecek
        this.basketBusinessRules.accountShouldBeExist(addItemToBasketRequest.accountId());
        // TODO: productId feign ile product service'ten kontrol edilecek
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
        // TODO: customer id feign ile account service'ten kontrol edilecek
        this.basketBusinessRules.accountShouldBeExist(removeItemFromBasketRequest.accountId());
        // TODO: productId feign ile product service'ten kontrol edilecek
        this.basketBusinessRules.productShouldBeExist(removeItemFromBasketRequest.productId());

        //TODO: business rule eklenecek mi?
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
    //TODO : cusomer silinirse sepetş boşalt. (Kafka ile gerçekleştirdik isterseniz bir bakın)
    @Override
    public void emptyBasket(String accountId) {
        this.basketRepository.delete(accountId);
    }

    @Override
    public Basket getById(String id) {
        return this.basketRepository.getById(id);
    }
}
