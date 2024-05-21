package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBusinessRules {

    private final MessageService messageService;
    private final ProductRepository productRepository;
    public void productShouldBeExist(Optional<Product> optionalProduct) {
        if (optionalProduct.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.ProductMessages.NOT_FOUND));
        }
    }
    public void productShouldNotBeDeleted(Optional<Product> product){
        if(product.get().getDeletedDate() != null){
            throw new BusinessException(messageService.getMessage(Messages.ProductMessages.DELETED));
        }
    }
}
