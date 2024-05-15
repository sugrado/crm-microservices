package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.CreatedProductResponse;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;

import java.util.List;

public interface ProductPropertyService {
    void add(int productId, CreateProductPropertyRequest request);
    void add(List<ProductPropertyDto> productPropertyDtoList, Product product);
}
