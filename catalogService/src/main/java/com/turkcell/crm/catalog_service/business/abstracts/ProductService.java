package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;

import java.util.List;

public interface ProductService {
    CreatedProductResponse add(CreateProductRequest createProductRequest);

    List<GetAllProductsResponse> getAll();

    public List<GetAllProductsByCategoryIdResponse> getAllByCategoryId(int categoryId);

    GetByIdProductResponse getById(int id);

    UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest);

    DeletedProductResponse delete(int id);

    Product getByIdForProductPropertyManager(int id);

    List<GetAllForCompleteOrderResponse> getAllForCompleteOrder(List<Integer> productIdList);
}
