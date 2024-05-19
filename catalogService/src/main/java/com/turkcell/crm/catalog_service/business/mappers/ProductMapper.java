package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.ProductPropertyDto;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
//    @Mapping(target = "properties", ignore = true)
    Product toProduct(CreateProductRequest createProductRequest);

    @Mapping(source = "category.id", target = "categoryId")
    CreatedProductResponse toCreatedProductResponse(Product product);

    @Mapping(source = "category.id", target = "categoryId")
    List<GetAllProductsResponse> toGetAllProductsResponse(List<Product> products);

    @Mapping(source = "property.name", target = "propertyName")
    ProductPropertyDto toProductPropertyDto(ProductProperty productProperty);

    GetByIdProductResponse toGetByIdProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(UpdateProductRequest updateProductRequest, @MappingTarget Product product);

    UpdatedProductResponse toUpdatedProductResponse(Product product);

    DeletedProductResponse toDeletedProductResponse(Product product);
}
