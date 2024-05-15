package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public void productIdShouldBeExist(int productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new BusinessException(Messages.ProductMessages.NOT_FOUND);
        }
    }
}
