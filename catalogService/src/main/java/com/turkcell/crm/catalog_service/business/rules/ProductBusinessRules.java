package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBusinessRules {
    public void productIdShouldBeExist(Optional<Product> optionalProduct) {
        if (optionalProduct.isEmpty()) {
            throw new BusinessException(Messages.ProductMessages.NOT_FOUND);
        }
    }
}
