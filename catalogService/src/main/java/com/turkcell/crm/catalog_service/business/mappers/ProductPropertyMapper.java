package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface ProductPropertyMapper {
    @Mapping(source = "propertyId", target = "property.id")
    ProductProperty toProductProperty(ProductPropertyDto productPropertyDto);
    @Mapping(source = "propertyId", target = "property.id")
    ProductProperty toProductProperty(CreateProductPropertyRequest request);
}
