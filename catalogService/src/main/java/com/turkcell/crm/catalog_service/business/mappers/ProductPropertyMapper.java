package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.UpdateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.*;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface ProductPropertyMapper {
    @Mapping(source = "propertyId", target = "property.id")
    ProductProperty toProductProperty(CreateProductPropertyRequest request);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    DeletedProductPropertyResponse toDeletedProductPropertyResponse(ProductProperty productProperty);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    CreatedProductPropertyResponse toCreatedProductPropertyResponse(ProductProperty productProperty);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    GetByIdProductPropertyResponse toGetByIdProductPropertyResponse(ProductProperty productProperty);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    GetAllProductPropertyResponse toGetAllProductPropertyResponse(ProductProperty productProperty);

    List<GetAllProductPropertyResponse> toGetAllProductPropertyResponse(List<ProductProperty> productProperties);


    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    GetAllProductPropertiesByProductIdResponse toGetAllProductPropertiesByProductIdResponse(ProductProperty productProperty);
    List<GetAllProductPropertiesByProductIdResponse> toGetAllProductPropertiesByProductIdResponse(List<ProductProperty> productProperties);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductPropertyFromRequest(UpdateProductPropertyRequest updateProductPropertyRequest, @MappingTarget ProductProperty productProperty);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "product.id", target = "productId")
    UpdatedProductPropertyResponse toUpdatedProductPropertyResponse(ProductProperty productProperty);
}
