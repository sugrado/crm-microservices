package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPropertyBusinessRules {
    private final ProductPropertyRepository productPropertyRepository;

    public void productPropertyShouldBeExists(Optional<ProductProperty> optionalProductProperty) {
        if (optionalProductProperty.isEmpty()) {
            throw new BusinessException(Messages.ProductPropertyMessages.NOT_FOUND);
        }
    }

    public void productPropertyShouldBeUnique(int productId, int propertyId) {
        if (this.productPropertyRepository.existsByProductIdAndPropertyId(productId, propertyId)) {
            throw new BusinessException(Messages.ProductPropertyMessages.ALREADY_EXISTS);
        }
    }
}
