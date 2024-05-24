package com.turkcell.crm.catalog_service.business.abstracts;

import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import com.turkcell.crm.catalog_service.entities.concretes.Category;

import java.util.List;

public interface CategoryService {
    CreatedCategoryResponse add(CreateCategoryRequest request);

    List<GetAllCategoriesResponse> getAll();

    GetByIdCategoryResponse getById(int id);

    UpdatedCategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest);

    DeletedCategoryResponse delete(int id);

    Category getByIdForProductManager(int id);
}
