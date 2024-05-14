package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;

import java.util.List;

public interface ProductService {
    CreatedProductResponse add(CreateProductRequest createProductRequest);

    List<GetAllProductsResponse> getAll();

    GetByIdProductResponse getById(int id);

    UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest);

    DeletedProductResponse delete(int id);
}
