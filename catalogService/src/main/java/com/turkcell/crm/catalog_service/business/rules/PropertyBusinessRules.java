package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyBusinessRules {

    private final PropertyRepository propertyRepository;
    private final MessageService messageService;

    public void propertyShouldBeExist(Optional<Property> optionalProperty) {
        if (optionalProperty.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND));
        }
    }

    public void propertyCanNotDuplicated(CreatePropertyRequest request) {
        Optional<Property> optionalProperty = this.propertyRepository.findByNameAndCategoryId(request.name(), request.categoryId());

        if (optionalProperty.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.PropertyMessages.ALREADY_EXISTS));
        }
    }
}
