package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.CreatedProductPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.DeletedProductPropertyResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface ProductPropertyMapper {
    @Mapping(source = "propertyId", target = "property.id")
    ProductProperty toProductProperty(CreateProductPropertyRequest request);

    DeletedProductPropertyResponse toDeletedProductPropertyResponse(ProductProperty productProperty);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    CreatedProductPropertyResponse toCreatedProductPropertyResponse(ProductProperty productProperty);
}
