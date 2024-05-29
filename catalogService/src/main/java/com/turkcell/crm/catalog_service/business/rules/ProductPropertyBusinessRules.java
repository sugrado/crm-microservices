package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPropertyBusinessRules {

    private final ProductPropertyRepository productPropertyRepository;
    private final MessageService messageService;

    public void productPropertyShouldBeExists(Optional<ProductProperty> optionalProductProperty) {
        if (optionalProductProperty.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.ProductPropertyMessages.NOT_FOUND));
        }
    }

    public void productPropertyShouldBeUnique(int productId, int propertyId) {
        if (this.productPropertyRepository.existsByProductIdAndPropertyId(productId, propertyId)) {
            throw new BusinessException(messageService.getMessage(Messages.ProductPropertyMessages.ALREADY_EXISTS));
        }
    }

    public void productPropertyShouldNotBeDeleted(Optional<ProductProperty> productProperty) {
        if (productProperty.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.ProductPropertyMessages.DELETED));
        }
    }

    public void productAndPropertyCategoryIdShouldBeMatch(Product product, Property property) {
        if (!product.getCategory().getId().equals(property.getCategory().getId())) {
            throw new BusinessException(messageService.getMessage(Messages.ProductPropertyMessages.PRODUCT_AND_PROPERTY_CATEGORY_ID_SHOULD_BE_MATCH));
        }
    }
}
