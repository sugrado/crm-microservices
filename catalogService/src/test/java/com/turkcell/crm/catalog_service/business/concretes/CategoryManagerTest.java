package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.constants.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.CreateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.category.UpdateCategoryRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.category.*;
import com.turkcell.crm.catalog_service.business.mappers.CategoryMapper;
import com.turkcell.crm.catalog_service.business.rules.CategoryBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryManagerTest {

    private CategoryRepository categoryRepository;
    private MessageService messageService;
    private CategoryManager categoryManager;
    private Category category;

    @BeforeEach
    void setUp() {
        messageService = mock(MessageService.class);
        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        categoryRepository = mock(CategoryRepository.class);
        CategoryBusinessRules categoryBusinessRules = new CategoryBusinessRules(categoryRepository, messageService);
        categoryManager = new CategoryManager(categoryRepository, categoryMapper, categoryBusinessRules);

        category = new Category();
        category.setId(1);
        category.setName("Test Name");
    }

    @Test
    void add_shouldThrowExceptionWhenCategoryAlreadyExists() {

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest("Test Name", "Test Description");
        CreatedCategoryResponse createdCategoryResponse = new CreatedCategoryResponse(1, "Test Name", "Test Description", LocalDateTime.now());
        String errorMessage = "Category already exists";
        when(categoryRepository.findByName(createCategoryRequest.name())).thenReturn(Optional.of(category));
        when(messageService.getMessage(anyString())).thenReturn(errorMessage);

        assertThrows(BusinessException.class, () -> {
            categoryManager.add(createCategoryRequest);
        });
    }

    @Test
    void add_shouldAddCategorySuccessfully() {

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest("Test Name", "Test Description");
        CreatedCategoryResponse createdCategoryResponse = new CreatedCategoryResponse(1, "Test Name", "Test Description", LocalDateTime.now());

        when(categoryRepository.findByName(createCategoryRequest.name())).thenReturn(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(category);


        CreatedCategoryResponse response = categoryManager.add(createCategoryRequest);

        assertEquals(createdCategoryResponse.id(), response.id());
        assertEquals(createdCategoryResponse.name(), response.name());
    }

    @Test
    void getAll_shouldReturnAllCategoriesSuccessfully() {

        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Test Name");
        category1.setDescription("Test Description");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Test Name2");
        category2.setDescription("Test Description");

        List<Category> categoryList = Arrays.asList(category1, category2);

        GetAllCategoriesResponse response1 = new GetAllCategoriesResponse(1, "Test Name", "Test Description");
        GetAllCategoriesResponse response2 = new GetAllCategoriesResponse(2, "Test Name2", "Test Description");
        List<GetAllCategoriesResponse> getAllCategoriesResponseList = Arrays.asList(response1, response2);

        when(categoryRepository.findAll()).thenReturn(categoryList);


        List<GetAllCategoriesResponse> response = categoryManager.getAll();

        assertEquals(getAllCategoriesResponseList.get(0), response.get(0));
        assertEquals(getAllCategoriesResponseList.get(1), response.get(1));
    }

    @Test
    void getById_shouldThrowNotFoundExceptionWhenCategoryNotFound() {

        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        String errorMessage = "Category not found";
        when(messageService.getMessage(Messages.CategoryMessages.NOT_FOUND)).thenReturn(errorMessage);

        assertThrows(NotFoundException.class, () -> {
            categoryManager.getById(1);
        });
    }

    @Test
    void getById_shouldReturnCategorySuccessfully() {

        GetByIdCategoryResponse getByIdCategoryResponse = new GetByIdCategoryResponse(1, "Test Name", "Test Description");
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        GetByIdCategoryResponse response = categoryManager.getById(1);


        assertEquals(getByIdCategoryResponse.id(), response.id());
        assertEquals(getByIdCategoryResponse.name(), response.name());
    }

    @Test
    void update_shouldThrowNotFoundExceptionWhenCategoryNotFound() {

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest("Name Updated", "Description Updated");
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.CategoryMessages.NOT_FOUND)).thenReturn("Category not found");

        assertThrows(NotFoundException.class, () -> {
            categoryManager.update(1, updateCategoryRequest);
        });
    }

    @Test
    void update_shouldThrowBusinessExceptionWhenCategoryNameAlreadyExists() {

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest("Electronics Updated", "Description Updated");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.findByNameAndIdIsNot("Electronics Updated", 1)).thenReturn(Optional.of(category));
        when(messageService.getMessage(Messages.CategoryMessages.ALREADY_EXISTS)).thenReturn("Category name already exists");

        assertThrows(BusinessException.class, () -> {
            categoryManager.update(1, updateCategoryRequest);
        });
    }

    @Test
    void update_shouldUpdateCategorySuccessfully() {

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.findByNameAndIdIsNot("Electronics Updated", 1)).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest("Electronics Updated", "Test Description");
        UpdatedCategoryResponse updatedCategoryResponse = new UpdatedCategoryResponse(1, "Electronics Updated", "Description Updated", LocalDateTime.now());


        UpdatedCategoryResponse response = categoryManager.update(1, updateCategoryRequest);

        assertEquals(updatedCategoryResponse.id(), response.id());
        assertEquals(updatedCategoryResponse.name(), response.name());
    }

    @Test
    void delete_shouldThrowNotFoundExceptionWhenCategoryNotFound() {

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.CategoryMessages.NOT_FOUND)).thenReturn("Category not found");

        assertThrows(NotFoundException.class, () -> {
            categoryManager.delete(1);
        });

    }

    @Test
    void delete_shouldDeleteCategorySuccessfully() {

        DeletedCategoryResponse deletedCategoryResponse = new DeletedCategoryResponse(1, "Test Name", "Test Description", LocalDateTime.now());

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        DeletedCategoryResponse response = categoryManager.delete(1);

        assertEquals(deletedCategoryResponse.id(), response.id());
    }

    @Test
    void getByIdEntity_shouldThrowNotFoundExceptionWhenCategoryNotFound() {

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.CategoryMessages.NOT_FOUND)).thenReturn("Category not found");

        assertThrows(NotFoundException.class, () -> {
            categoryManager.getByIdEntity(1);
        });
    }

    @Test
    void getByIdEntity_shouldReturnCategorySuccessfully() {

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        Category result = categoryManager.getByIdEntity(1);

        assertEquals(category.getId(), result.getId());
    }
}