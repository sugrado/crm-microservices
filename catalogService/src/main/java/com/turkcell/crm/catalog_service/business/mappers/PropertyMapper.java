package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetByIdPropertyResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface PropertyMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Property toProperty(CreatePropertyRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    CreatedPropertyResponse toCreatedPropertyResponse(Property property);

    List<GetAllPropertiesByCategoryIdResponse> toGetAllPropertiesByCategoryIdResponse(List<Property> propertyList);

    @Mapping(source = "category.id", target = "categoryId")
    List<GetAllPropertiesResponse> toGetAllPropertiesResponse(List<Property> propertyList);

    GetByIdPropertyResponse toGetByIdPropertyResponse(Property property);
}
