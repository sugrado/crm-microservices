package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.Messages;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
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
}
