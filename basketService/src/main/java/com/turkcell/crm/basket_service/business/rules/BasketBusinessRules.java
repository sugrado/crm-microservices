package com.turkcell.crm.basket_service.business.rules;

import com.turkcell.crm.basket_service.api.clients.AccountClient;
import com.turkcell.crm.basket_service.api.clients.CatalogClient;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketBusinessRules {
    private final BasketRepository basketRepository;
    //private final CustomerClient customerClient;
    private final AccountClient accountClient;
    private final CatalogClient catalogClient;

    public void accountShouldBeExist(String stringAccountId) {
        int accountId = Integer.parseInt(stringAccountId);
        this.accountClient.checkIfAccountExist(accountId);
    }

    public void productShouldBeExist(String stringProductId) {
        int productId = Integer.parseInt(stringProductId);
        this.catalogClient.checkIfProductExist(productId);
    }

}
