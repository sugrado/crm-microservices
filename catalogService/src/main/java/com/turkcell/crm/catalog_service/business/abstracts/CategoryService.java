package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.CreatedCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.GetAllCategoriesResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.GetByIdCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.UpdatedCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.DeletedCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.DeletedProductResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.GetAllProductsResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.GetByIdProductResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.UpdatedProductResponse;

import java.util.List;

public interface CategoryService {

    CreatedCategoryResponse add(CreateCategoryRequest request);
    List<GetAllCategoriesResponse> getAll();

    GetByIdCategoryResponse getById(int id);

    UpdatedCategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest);

    DeletedCategoryResponse delete(int id);
}
