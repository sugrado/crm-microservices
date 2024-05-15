package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.CreatedProductResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface ProductMapper {
    Product toProduct(CreateProductRequest createProductRequest);
    CreatedProductResponse toCreatedProductResponse(Product product);
}
