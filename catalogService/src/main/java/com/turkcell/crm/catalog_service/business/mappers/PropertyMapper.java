package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface PropertyMapper {
    List<Property> toProperty(List<CreatePropertyRequest> request);

    Property toProperty(CreatePropertyRequest request);
    List<CreatedPropertyResponse> toCreatedPropertyResponse(List<Property> properties);
    CreatedPropertyResponse toCreatedPropertyResponse(Property property);
}
