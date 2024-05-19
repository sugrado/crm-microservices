package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CategoryMapper {
    Category toCategory(CreateCategoryRequest request);

    CreatedCategoryResponse toCreatedCategoryResponse(Category category);

    List<GetAllCategoriesResponse> toGetAllCategoriesResponseList(List<Category> categoryList);

    GetByIdCategoryResponse toGetByIdCategoryResponse(Category category);

    UpdatedCategoryResponse toUpdatedCategoryResponse(Category category);

    DeletedCategoryResponse toDeletedCategoryResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(UpdateCategoryRequest updateCategoryRequest, @MappingTarget Category category);
}
