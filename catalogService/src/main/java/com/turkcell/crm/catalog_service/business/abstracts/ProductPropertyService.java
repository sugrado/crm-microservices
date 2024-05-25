package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.UpdateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.*;

import java.util.List;

public interface ProductPropertyService {
    CreatedProductPropertyResponse add(int productId, CreateProductPropertyRequest request);

    List<GetAllProductPropertyResponse> getAll();

    public List<GetAllProductPropertiesByProductIdResponse> getAllByProductId(int productId);

    GetByIdProductPropertyResponse getById(int productId, int id);

    UpdatedProductPropertyResponse update(int id, UpdateProductPropertyRequest updateProductPropertyRequest);

    DeletedProductPropertyResponse delete(int id);

}
