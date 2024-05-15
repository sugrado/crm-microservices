package com.turkcell.crm.catalog_service.business.concretes;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyManager implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyBusinessRules propertyBusinessRules;

    @Override
    public List<Property> add(List<CreatePropertyRequest> request) {
        List<Property> properties = this.propertyMapper.toProperty(request);
        return this.propertyRepository.saveAll(properties);
    }

    @Override
    public List<Property> getAllById(List<Integer> ids){
        return this.propertyRepository.findAllByIdIsIn(ids);
    }
}
