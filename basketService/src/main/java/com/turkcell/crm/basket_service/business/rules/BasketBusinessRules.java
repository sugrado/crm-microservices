package com.turkcell.crm.basket_service.business.rules;

import com.turkcell.crm.basket_service.api.clients.AccountClient;
import com.turkcell.crm.basket_service.api.clients.CatalogClient;
import com.turkcell.crm.basket_service.business.constants.Messages;
import com.turkcell.crm.basket_service.core.business.abstracts.MessageService;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketBusinessRules {
    private final BasketRepository basketRepository;
    private final AccountClient accountClient;
    private final CatalogClient catalogClient;
    private final MessageService messageService;

    public void accountShouldBeExist(String stringAccountId) {
        int accountId = Integer.parseInt(stringAccountId);
        this.accountClient.checkIfAccountExist(accountId);
    }

    public void productShouldBeExist(String stringProductId) {
        int productId = Integer.parseInt(stringProductId);
        this.catalogClient.getById(productId);
    }

    public void productStockShouldBeEnough(int unitsInStock) {
        if (unitsInStock < 1) {
            throw new BusinessException(this.messageService.getMessage(Messages.BasketMessages.PRODUCT_STOCK_NOT_ENOUGH));
        }
    }

    public void basketShouldBeExist(Optional<Basket> basket) {
        if (basket.isEmpty()) {
            throw new NotFoundException(this.messageService.getMessage(Messages.BasketMessages.BASKET_NOT_FOUND));
        }
    }

    public void itemShouldBeExist(Optional<BasketItem> item) {
        if (item.isEmpty()) {
            throw new NotFoundException(this.messageService.getMessage(Messages.BasketMessages.BASKET_ITEM_NOT_FOUND));
        }
    }
}
