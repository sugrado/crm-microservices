package com.turkcell.crm.basket_service.business.concretes;

import com.turkcell.crm.basket_service.api.clients.CatalogClient;
import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.business.mappers.BasketMapper;
import com.turkcell.crm.basket_service.business.rules.BasketBusinessRules;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import com.turkcell.crm.common.shared.dtos.catalogs.GetByIdProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {
    private final BasketRepository basketRepository;
    private final BasketBusinessRules basketBusinessRules;
    private final BasketMapper basketMapper;
    private final CatalogClient catalogClient;

    @Override
    public void addItem(AddItemToBasketRequest addItemToBasketRequest) {
        GetByIdProductResponse product = this.catalogClient.getById(Integer.parseInt(addItemToBasketRequest.productId()));
        this.basketBusinessRules.accountShouldBeExist(addItemToBasketRequest.accountId());
        this.basketBusinessRules.productStockShouldBeEnough(product.unitsInStock());

        Basket basket;
        Optional<Basket> basketOptional = this.basketRepository.getById(addItemToBasketRequest.accountId());
        basket = basketOptional.orElseGet(() -> new Basket(addItemToBasketRequest.accountId()));
        basket.getItems().add(new BasketItem(addItemToBasketRequest.productId()));
        this.basketRepository.addOrUpdate(basket);
    }

    @Override
    public void removeItemFromBasket(RemoveItemFromBasketRequest removeItemFromBasketRequest) {
        this.basketBusinessRules.accountShouldBeExist(removeItemFromBasketRequest.accountId());
        this.basketBusinessRules.productShouldBeExist(removeItemFromBasketRequest.productId());

        Optional<Basket> basketOptional = this.basketRepository.getById(removeItemFromBasketRequest.accountId());
        this.basketBusinessRules.basketShouldBeExist(basketOptional);
        Basket basket = basketOptional.get();

        List<BasketItem> items = basket.getItems();
        boolean deleted = this.deleteBasketIfEmpty(removeItemFromBasketRequest.accountId(), items);
        if (deleted) return;

        Optional<BasketItem> item = items.stream()
                .filter(p -> p.getProductId().equals(removeItemFromBasketRequest.productId()))
                .findFirst();

        this.basketBusinessRules.itemShouldBeExist(item);

        items.remove(item.get());
        this.basketRepository.addOrUpdate(basket);
    }

    private boolean deleteBasketIfEmpty(String id, List<BasketItem> items) {
        if (items.size() < 2) {
            this.basketRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public void emptyBasket(String accountId) {
        Optional<Basket> basketOptional = this.basketRepository.getById(accountId);
        this.basketBusinessRules.basketShouldBeExist(basketOptional);
        this.basketRepository.delete(accountId);
    }

    @Override
    public Basket getById(String id) {
        Optional<Basket> basketOptional = this.basketRepository.getById(id);
        this.basketBusinessRules.basketShouldBeExist(basketOptional);
        return basketOptional.get();
    }

    @Override
    public List<GetProductsFromBasketDto> getProductsFromBasket(String id) {
        Optional<Basket> basketOptional = this.basketRepository.getById(id);
        this.basketBusinessRules.basketShouldBeExist(basketOptional);
        return this.basketMapper.toGetProductsFromBasketDto(basketOptional.get().getItems());
    }

    @Override
    public void removeProductFromBaskets(int id) {
        Map<String, Basket> basketsMap = this.basketRepository.getAll();
        List<Basket> baskets = List.copyOf(basketsMap.values());
        baskets.forEach(basket -> {
            Optional<BasketItem> item = basket.getItems()
                    .stream()
                    .filter(p -> p.getProductId().equals(String.valueOf(id)))
                    .findFirst();

            if (item.isPresent()) {
                boolean deleted = this.deleteBasketIfEmpty(basket.getAccountId(), basket.getItems());
                if (deleted) return;

                basket.getItems().remove(item.get());
                this.basketRepository.addOrUpdate(basket);
            }
        });
    }
}
