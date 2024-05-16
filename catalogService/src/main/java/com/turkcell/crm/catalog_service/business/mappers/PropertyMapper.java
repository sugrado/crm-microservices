package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface PropertyMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Property toProperty(CreatePropertyRequest request);
    @Mapping(source = "category.name", target = "categoryName")
    CreatedPropertyResponse toCreatedPropertyResponse(Property property);
    @Mapping(source = "category.id", target = "categoryId")
    List<GetAllPropertiesByCategoryIdResponse> toGetAllPropertiesResponse(List<Property> propertyList);
}
