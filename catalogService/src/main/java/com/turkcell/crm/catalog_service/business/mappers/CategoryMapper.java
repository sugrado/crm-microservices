package com.turkcell.crm.catalog_service.business.mappers;

import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.CreatedCategoryResponse;
import com.turkcell.crm.catalog_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface CategoryMapper {
    Category toCategory(CreateCategoryRequest request);
    CreatedCategoryResponse toCreatedCategoryResponse(Category category);
}
