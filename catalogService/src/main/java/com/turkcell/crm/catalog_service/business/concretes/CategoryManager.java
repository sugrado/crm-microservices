package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import com.turkcell.crm.catalog_service.business.mappers.CategoryMapper;
import com.turkcell.crm.catalog_service.business.rules.CategoryBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public CreatedCategoryResponse add(CreateCategoryRequest request) {
        this.categoryBusinessRules.categoryNameCannotBeDuplicatedWhenInserted(request.name());
        Category categoryToSave = this.categoryMapper.toCategory(request);
        Category createdCategory = this.categoryRepository.save(categoryToSave);
        return this.categoryMapper.toCreatedCategoryResponse(createdCategory);
    }

    @Override
    public List<GetAllCategoriesResponse> getAll() {

        List<Category> categories = this.categoryRepository.findAll();
        return this.categoryMapper.toGetAllCategoriesResponseList(categories);
    }

    @Override
    public GetByIdCategoryResponse getById(int id) {

        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        this.categoryBusinessRules.categoryShouldBeExist(optionalCategory);
        Category category = optionalCategory.get();
        return this.categoryMapper.toGetByIdCategoryResponse(category);
    }

    @Override
    public UpdatedCategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        this.categoryBusinessRules.categoryShouldBeExist(optionalCategory);
        this.categoryBusinessRules.categoryNameCannotBeDuplicatedWhenUpdated(id, updateCategoryRequest.name());
        Category category = optionalCategory.get();

        this.categoryMapper.updateCategoryFromRequest(updateCategoryRequest, category);
        Category updatedCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toUpdatedCategoryResponse(updatedCategory);
    }

    @Override
    public DeletedCategoryResponse delete(int id) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        this.categoryBusinessRules.categoryShouldBeExist(optionalCategory);

        Category category = optionalCategory.get();
        category.setDeletedDate(LocalDateTime.now());
        Category deletedCategory = this.categoryRepository.save(category);

        return this.categoryMapper.toDeletedCategoryResponse(deletedCategory);
    }
}
