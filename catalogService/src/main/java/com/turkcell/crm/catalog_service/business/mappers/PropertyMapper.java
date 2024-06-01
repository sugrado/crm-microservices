package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.UpdatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.*;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface PropertyMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Property toProperty(CreatePropertyRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    CreatedPropertyResponse toCreatedPropertyResponse(Property property);

    @Mapping(source = "category.id", target = "categoryId")
    GetAllPropertiesResponse toGetAllPropertiesResponse(Property property);

    List<GetAllPropertiesResponse> toGetAllPropertiesResponse(List<Property> propertyList);

    List<GetAllPropertiesByCategoryIdResponse> toGetAllPropertiesByCategoryIdResponse(List<Property> propertyList);

    @Mapping(source = "category.id", target = "categoryId")
    GetByIdPropertyResponse toGetByIdPropertyResponse(Property property);

    @Mapping(source = "category.id", target = "categoryId")
    DeletePropertyResponse toDeletePropertyResponse(Property property);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePropertyFromRequest(UpdatePropertyRequest updatePropertyRequest, @MappingTarget Property property);

    @Mapping(source = "category.id", target = "categoryId")
    UpdatedPropertyResponse toUpdatedPropertyResponse(Property property);
}
