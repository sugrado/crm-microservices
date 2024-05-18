package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.CreatedCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.GetAllCategoriesResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.GetByIdCategoryResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.UpdatedCategoryResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CategoryMapper {
    Category toCategory(CreateCategoryRequest request);
    CreatedCategoryResponse toCreatedCategoryResponse(Category category);
    List<GetAllCategoriesResponse> toGetAllCategoriesResponseList(List<Category> categoryList);
    GetByIdCategoryResponse toGetByIdCategoryResponse(Category category);
    UpdatedCategoryResponse toUpdatedCategoryResponse(Category category);
}
