package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.constants.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.UpdatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.*;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
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

class PropertyManagerTest {

    private PropertyRepository propertyRepository;
    private MessageService messageService;
    private PropertyManager propertyManager;
    private CreatePropertyRequest createPropertyRequest;
    private Property property;
    private CategoryManager categoryManager;

    @BeforeEach
    void setup() {

        PropertyMapper propertyMapper = Mappers.getMapper(PropertyMapper.class);
        propertyRepository = mock(PropertyRepository.class);
        messageService = mock(MessageService.class);
        PropertyBusinessRules propertyBusinessRules = new PropertyBusinessRules(propertyRepository, messageService);
        categoryManager = mock(CategoryManager.class);
        propertyManager = new PropertyManager(propertyRepository, propertyMapper, propertyBusinessRules, categoryManager);

        property = new Property();
        property.setId(1);
        property.setName("Test Name");
        property.setCategory(new Category(1));
    }

    @Test
    void add_shouldThrowBusinessExceptionWhenPropertyAlreadyExist() {

        createPropertyRequest = new CreatePropertyRequest("Test Name", 1);

        String errorMessage = "Property already exists";
        when(propertyRepository.findByNameAndCategoryId(anyString(), anyInt())).thenReturn(Optional.of(property));
        when(messageService.getMessage(Messages.PropertyMessages.ALREADY_EXISTS)).thenReturn(errorMessage);

        assertThrows(BusinessException.class, () -> {
            propertyManager.add(createPropertyRequest);
        });
    }

    @Test
    void add_shouldAddPropertySuccessfully() {

        CreatePropertyRequest createPropertyRequest = new CreatePropertyRequest("Test Name", 1);
        CreatedPropertyResponse createdPropertyResponse = new CreatedPropertyResponse(1, 1, "Test Name", LocalDateTime.now());

        when(propertyRepository.findByNameAndCategoryId(createPropertyRequest.name(), createPropertyRequest.categoryId())).thenReturn(Optional.empty());
        when(propertyRepository.save(any())).thenReturn(property);


        CreatedPropertyResponse response = propertyManager.add(createPropertyRequest);

        assertEquals(createdPropertyResponse.id(), response.id());
        assertEquals(createdPropertyResponse.name(), response.name());
        assertEquals(createdPropertyResponse.categoryId(), response.categoryId());
    }

    @Test
    void getAll_shouldReturnAllPropertiesSuccessfully() {

        Property property1 = new Property();
        property1.setId(1);
        property1.setName("Test Name");
        property1.setCategory(new Category(1));

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("Test Name 2");
        property2.setCategory(new Category(1));

        List<Property> propertyList = Arrays.asList(property1, property2);

        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse(1, "Test Name", 1);
        GetAllPropertiesResponse getAllPropertiesResponse1 = new GetAllPropertiesResponse(2, "Test Name 2", 1);

        List<GetAllPropertiesResponse> responseList = Arrays.asList(getAllPropertiesResponse, getAllPropertiesResponse1);

        when(propertyRepository.findAll()).thenReturn(propertyList);
        List<GetAllPropertiesResponse> result = propertyManager.getAll();

        assertEquals(responseList.get(0), result.get(0));
        assertEquals(responseList.get(1), result.get(1));
    }

    @Test
    void delete_shouldThrowBusinessExceptionWhenPropertyNotFound() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(NotFoundException.class, () -> {
            propertyManager.delete(anyInt());
        });
    }

    @Test
    void delete_shouldDeletePropertySuccessfully() {

        DeletePropertyResponse deletePropertyResponse = new DeletePropertyResponse(1,
                1, "Test Name", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        DeletePropertyResponse response = propertyManager.delete(anyInt());

        assertEquals(deletePropertyResponse.id(), response.id());
        assertEquals(deletePropertyResponse.name(), response.name());
        assertEquals(deletePropertyResponse.categoryId(), response.categoryId());
    }

    @Test
    void update_shouldThrowExceptionWhenPropertyNotFound() {

        UpdatePropertyRequest updatePropertyRequest = new UpdatePropertyRequest("Test Name", 1);
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> propertyManager.update(1, updatePropertyRequest));

    }

    @Test
    void update_shouldUpdatePropertySuccessfully() {

        Category category = new Category();
        UpdatePropertyRequest updatePropertyRequest = new UpdatePropertyRequest("Test Name", 1);
        UpdatedPropertyResponse updatedPropertyResponse = new UpdatedPropertyResponse(1,
                1, "Test Name", LocalDateTime.now(), LocalDateTime.now());

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));
        when(categoryManager.getByIdEntity(anyInt())).thenReturn(category);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);


        UpdatedPropertyResponse response = propertyManager.update(1, updatePropertyRequest);

        assertEquals(updatedPropertyResponse.id(), response.id());
    }

    @Test
    void getByIdForProductPropertyManager_shouldThrowBusinessExceptionWhenPropertyNotFound() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(NotFoundException.class, () -> {
            propertyManager.getByIdForProductPropertyManager(1);
        });
    }

    @Test
    void getByIdForProductPropertyManager_shouldReturnPropertySuccessfully() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));

        Property result = propertyManager.getByIdForProductPropertyManager(anyInt());

        assertEquals(property.getId(), result.getId());
        assertEquals(property.getName(), result.getName());

    }

    @Test
    void getAllByCategoryId_shouldReturnAllPropertiesForCategorySuccessfully() {

        int categoryId = 1;

        Property property1 = new Property();
        property1.setId(1);
        property1.setName("Test Name");

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("Test Name 2");

        List<Property> propertyList = Arrays.asList(property1, property2);

        GetAllPropertiesByCategoryIdResponse response1 = new GetAllPropertiesByCategoryIdResponse(1, "Test Name");
        GetAllPropertiesByCategoryIdResponse response2 = new GetAllPropertiesByCategoryIdResponse(2, "Test Name 2");
        List<GetAllPropertiesByCategoryIdResponse> responseList = Arrays.asList(response1, response2);

        when(propertyRepository.findAllByCategoryId(categoryId)).thenReturn(propertyList);


        List<GetAllPropertiesByCategoryIdResponse> result = propertyManager.getAllByCategoryId(categoryId);


        assertEquals(responseList.get(0), result.get(0));
        assertEquals(responseList.get(1), result.get(1));
    }

    @Test
    void getById_shouldThrowBusinessExceptionWhenPropertyNotFound() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(NotFoundException.class, () -> {
            propertyManager.getById(1);
        });
    }

    @Test
    void getById_shouldReturnPropertyResponseSuccessfully() {

        GetByIdPropertyResponse getByIdPropertyResponse = new GetByIdPropertyResponse("Test Name", 1);
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));

        GetByIdPropertyResponse result = propertyManager.getById(1);

        assertEquals(getByIdPropertyResponse.name(), result.name());
        assertEquals(getByIdPropertyResponse.categoryId(), result.categoryId());
    }
}