package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import com.turkcell.crm.catalog_service.business.mappers.CategoryMapper;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CreatedCategoryResponse add(CreateCategoryRequest request) {

        Category category = this.categoryMapper.toCategory(request);
        this.categoryRepository.save(category);
        return this.categoryMapper.toCreatedCategoryResponse(category);
    }

    @Override
    public List<GetAllCategoriesResponse> getAll() {

        List<Category> categories = this.categoryRepository.findAll();
        return this.categoryMapper.toGetAllCategoriesResponseList(categories);
    }

    @Override
    public GetByIdCategoryResponse getById(int id) {

        Optional<Category> optionalCategory = this.categoryRepository.findById(id);

        return this.categoryMapper.toGetByIdCategoryResponse(optionalCategory.get());
    }

    @Override
    public UpdatedCategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);

        Category category = optionalCategory.get();
        Category updatedCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toUpdatedCategoryResponse(updatedCategory);
    }

    @Override
    public DeletedCategoryResponse delete(int id) {
        return null;
    }
}
