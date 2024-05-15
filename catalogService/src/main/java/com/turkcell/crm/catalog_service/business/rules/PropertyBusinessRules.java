package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyBusinessRules {
    private final PropertyRepository propertyRepository;

    public void propertyIdShouldBeExist(int propertyId){
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if(optionalProperty.isEmpty()){
            throw new BusinessException(Messages.PropertyMessages.NOT_FOUND);
        }
    }
}
