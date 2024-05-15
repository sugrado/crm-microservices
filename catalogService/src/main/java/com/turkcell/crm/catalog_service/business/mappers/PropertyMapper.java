package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface PropertyMapper {
    List<Property> toProperty(List<CreatePropertyRequest> request);
}
