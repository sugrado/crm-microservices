package com.turkcell.crm.basket_service.api.controllers;

import com.turkcell.crm.basket_service.business.abstracts.BasketService;
import com.turkcell.crm.basket_service.business.dtos.requests.AddItemToBasketRequest;
import com.turkcell.crm.basket_service.business.dtos.requests.RemoveItemFromBasketRequest;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket-service/api/v1/baskets")
@RequiredArgsConstructor
public class BasketsController {
    private final BasketService basketService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addItem(@RequestBody @Valid AddItemToBasketRequest addItemToBasketRequest) {
        basketService.addItem(addItemToBasketRequest);
    }

    @PostMapping("remove-item")
    @ResponseStatus(HttpStatus.OK)
    public void removeItem(@RequestBody @Valid RemoveItemFromBasketRequest removeItemFromBasketRequest) {
        basketService.removeItemFromBasket(removeItemFromBasketRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void emptyBasket(@PathVariable String id) {
        basketService.emptyBasket(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getById(@PathVariable String id) {
        return basketService.getById(id);
    }

    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductsFromBasketDto> getProductsFromBasket(@PathVariable String id) {
        return basketService.getProductsFromBasket(id);
    }
}
