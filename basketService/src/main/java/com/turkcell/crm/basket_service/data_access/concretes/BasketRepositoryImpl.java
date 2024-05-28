package com.turkcell.crm.basket_service.data_access.concretes;

import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BasketRepositoryImpl implements BasketRepository {
    public static final String KEY = "Basket";
    private final HashOperations<String, String, Basket> hashOperations;

    public BasketRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Map<String, Basket> getAll() {
        return this.hashOperations.entries(KEY);
    }

    public Basket getById(String id) {
        return this.hashOperations.get(KEY, id);
    }

    public void addOrUpdate(Basket basket) {
        this.hashOperations.put(KEY, basket.getCustomerId(), basket);
    }

    public void addItemToBasket(String customerId, BasketItem basketItem) {
        Basket basket = this.getById(customerId);
        if (basket == null) {
            basket = new Basket(customerId);
        }
        basket.getItems().add(basketItem);
        this.hashOperations.put(KEY, customerId, basket);
    }

    public void removeItemFromBasket(String customerId, String productId) {
        Basket basket = this.hashOperations.get(KEY, customerId);
        if (basket == null) {
            return;
        }
        List<BasketItem> items = basket.getItems();
        Optional<BasketItem> item = items.stream().filter(p -> p.getProductId().equals(productId)).findFirst();
        if (item.isEmpty()) {
            return;
        }
        items.remove(item.get());
        this.hashOperations.put(KEY, customerId, basket);
    }

    public void delete(String id) {
        this.hashOperations.delete(KEY, id);
    }
}
