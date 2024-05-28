package com.turkcell.crm.basket_service.business.rules;

import com.turkcell.crm.basket_service.api.clients.CatalogClient;
import com.turkcell.crm.basket_service.api.clients.CustomerClient;
import com.turkcell.crm.basket_service.data_access.abstracts.BasketRepository;
import com.turkcell.crm.basket_service.entities.concretes.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketBusinessRules {
    private final BasketRepository basketRepository;
    private final CustomerClient customerClient;
    private final CatalogClient catalogClient;

    public void customerShouldBeExist(String stringCustomerId){
        int customerId= Integer.parseInt(stringCustomerId);
        this.customerClient.checkIfCustomerExists(customerId);
    }

    public void productShouldBeExist(String stringProductId){
        int productId = Integer.parseInt(stringProductId);
        this.catalogClient.checkIfProductExist(productId);
    }

}
