package com.turkcell.crm.basket_service.data_access.concretes;

import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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

    public Optional<Basket> getById(String id) {
        Basket basket = this.hashOperations.get(KEY, id);
        if (basket == null) {
            return Optional.empty();
        }
        return Optional.of(basket);
    }

    public void addOrUpdate(Basket basket) {
        this.hashOperations.put(KEY, basket.getAccountId(), basket);
    }

    public void delete(String id) {
        this.hashOperations.delete(KEY, id);
    }
}
