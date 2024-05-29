package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.*;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapper;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapperImpl;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    void setup() {

        PropertyMapper propertyMapper = new PropertyMapperImpl();
        propertyRepository = mock(PropertyRepository.class);
        messageService = mock(MessageService.class);
        PropertyBusinessRules propertyBusinessRules = new PropertyBusinessRules(propertyRepository, messageService);
        propertyManager = new PropertyManager(propertyRepository, propertyMapper, propertyBusinessRules);

        property = new Property();
        property.setId(1);
        property.setName("Test Name");
        property.setCategory(new Category(1));
    }

    @Test
    void add_withExistingProperty_shouldThrowBusinessException() {

        createPropertyRequest = new CreatePropertyRequest("Test Name", 1);

        String errorMessage = "Property already exists";
        when(propertyRepository.findByNameAndCategoryId(anyString(), anyInt())).thenReturn(Optional.of(property));
        when(messageService.getMessage(Messages.PropertyMessages.ALREADY_EXISTS)).thenReturn(errorMessage);

        assertThrows(BusinessException.class, () -> {
            propertyManager.add(createPropertyRequest);
        });
    }

    @Test
    void add_withNewProperty_shouldCreatePropertySuccessfully() {

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
    void getAll_shouldReturnAllProperties() {
        Property property1 = new Property();
        property1.setId(1);
        property1.setName("Test Name");
        property1.setCategory(new Category(1));

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("Test Name 2");
        property2.setCategory(new Category(1));

        List<Property> propertyList = Arrays.asList(property1, property2);

        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse("Test Name", "1");
        GetAllPropertiesResponse getAllPropertiesResponse1 = new GetAllPropertiesResponse("Test Name 2", "1");

        List<GetAllPropertiesResponse> responseList = Arrays.asList(getAllPropertiesResponse, getAllPropertiesResponse1);

        when(propertyRepository.findAll()).thenReturn(propertyList);
        List<GetAllPropertiesResponse> result = propertyManager.getAll();

        assertEquals(responseList.get(0), result.get(0));
        assertEquals(responseList.get(1), result.get(1));
    }

    @Test
    void delete_withPropertyNotFound_shouldThrowBusinessException() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(BusinessException.class, () -> {
            propertyManager.delete(anyInt());
        });
    }

    @Test
    void delete_withDeletedProperty_shouldThrowBusinessException() {

        property.setDeletedDate(LocalDateTime.now());
        when(propertyRepository.findById(1)).thenReturn(Optional.of(property));
        when(messageService.getMessage(Messages.PropertyMessages.DELETED)).thenReturn("Property is already deleted");

        assertThrows(BusinessException.class, () -> {
            propertyManager.delete(1);
        });
    }

    @Test
    void delete_withValidProperty_shouldDeletePropertySuccessfully() {

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
    void getByIdForProductPropertyManager_withPropertyNotFound_shouldThrowBusinessException() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(BusinessException.class, () -> {
            propertyManager.getByIdForProductPropertyManager(1);
        });
    }

    @Test
    void getByIdForProductPropertyManager_withDeletedProperty_shouldThrowBusinessException() {

        property.setDeletedDate(LocalDateTime.now());
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));
        when(messageService.getMessage(Messages.PropertyMessages.DELETED)).thenReturn("Property is already deleted");

        assertThrows(BusinessException.class, () -> {
            propertyManager.getByIdForProductPropertyManager(anyInt());
        });
    }

    @Test
    void getByIdForProductPropertyManager_withValidProperty_shouldReturnProperty() {

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));

        Property result = propertyManager.getByIdForProductPropertyManager(anyInt());

        assertEquals(property.getId(), result.getId());
        assertEquals(property.getName(), result.getName());

    }

    @Test
    void getAllByCategoryId_shouldReturnAllPropertiesForCategory() {

        int categoryId = 1;

        Property property1 = new Property();
        property1.setId(1);
        property1.setName("Test Name");

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("Test Name 2");

        List<Property> propertyList = Arrays.asList(property1, property2);

        GetAllPropertiesByCategoryIdResponse response1 = new GetAllPropertiesByCategoryIdResponse("Test Name");
        GetAllPropertiesByCategoryIdResponse response2 = new GetAllPropertiesByCategoryIdResponse("Test Name 2");
        List<GetAllPropertiesByCategoryIdResponse> responseList = Arrays.asList(response1, response2);

        when(propertyRepository.findAllByCategoryId(categoryId)).thenReturn(propertyList);


        List<GetAllPropertiesByCategoryIdResponse> result = propertyManager.getAllByCategoryId(categoryId);


        assertEquals(responseList.get(0), result.get(0));
        assertEquals(responseList.get(1), result.get(1));
    }

    @Test
    void getById_withPropertyNotFound_shouldThrowBusinessException() {
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.PropertyMessages.NOT_FOUND)).thenReturn("Property not found");

        assertThrows(BusinessException.class, () -> {
            propertyManager.getById(1);
        });
    }

    @Test
    void getById_withDeletedProperty_shouldThrowBusinessException() {
        property.setDeletedDate(LocalDateTime.now());
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));
        when(messageService.getMessage(Messages.PropertyMessages.DELETED)).thenReturn("Property is already deleted");

        assertThrows(BusinessException.class, () -> {
            propertyManager.getById(1);
        });
    }

    @Test
    void getById_withValidProperty_shouldReturnGetByIdPropertyResponse() {

        GetByIdPropertyResponse getByIdPropertyResponse = new GetByIdPropertyResponse("Test Name", 1);
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));

        GetByIdPropertyResponse result = propertyManager.getById(1);

        assertEquals(getByIdPropertyResponse.name(), result.name());
        assertEquals(getByIdPropertyResponse.categoryId(), result.categoryId());
    }

}