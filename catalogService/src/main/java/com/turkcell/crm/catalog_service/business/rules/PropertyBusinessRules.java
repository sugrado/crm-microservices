package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyBusinessRules {
    public void propertyShouldBeExist(Optional<Property> optionalProperty) {
        if (optionalProperty.isEmpty()) {
            throw new BusinessException(Messages.PropertyMessages.NOT_FOUND);
        }
    }
}
