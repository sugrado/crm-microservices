package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.constants.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.UpdateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.*;
import com.turkcell.crm.catalog_service.business.mappers.ProductPropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductPropertyBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductPropertyManagerTest {

    private ProductPropertyManager productPropertyManager;
    private ProductPropertyRepository productPropertyRepository;
    private ProductProperty productProperty;
    private Property property;
    private PropertyService propertyService;
    private ProductService productService;
    private Product product;
    private MessageService messageService;

    @BeforeEach
    void setUp() {

        productPropertyRepository = mock(ProductPropertyRepository.class);
        ProductPropertyMapper productPropertyMapper = Mappers.getMapper(ProductPropertyMapper.class);
        productService = mock(ProductService.class);
        propertyService = mock(PropertyService.class);
        messageService = mock(MessageService.class);
        ProductPropertyBusinessRules productPropertyBusinessRules = new ProductPropertyBusinessRules(productPropertyRepository, messageService);

        productPropertyManager = new ProductPropertyManager(productPropertyRepository,
                productPropertyMapper, productService, propertyService, productPropertyBusinessRules);

        product = new Product();
        product.setId(1);
        product.setTitle("Test Title");
        product.setCategory(new Category(1));

        property = new Property();
        property.setId(1);
        property.setName("Test Name");
        property.setCategory(new Category(1));

        productProperty = new ProductProperty();
        productProperty.setId(1);
        productProperty.setProduct(product);
        productProperty.setProperty(property);

    }

    @Test
    void add_shouldThrowExceptionWhenProductAndPropertyCategoryMismatch() {

        CreateProductPropertyRequest createProductPropertyRequest = new CreateProductPropertyRequest("Test Value", 1);

        product.setCategory(new Category(1));
        property.setCategory(new Category(2));

        when(productService.getByIdForProductPropertyManager(anyInt())).thenReturn(product);
        when(propertyService.getByIdForProductPropertyManager(anyInt())).thenReturn(property);
        when(messageService.getMessage(Messages.ProductPropertyMessages.PRODUCT_AND_PROPERTY_CATEGORY_ID_SHOULD_BE_MATCH)).thenReturn("Product and Property category IDs should match");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productPropertyManager.add(1, createProductPropertyRequest);
        });

        assertEquals("Product and Property category IDs should match", exception.getMessage());

    }

    @Test
    void add_shouldThrowExceptionWhenProductPropertyAlreadyExists() {

        CreateProductPropertyRequest createProductPropertyRequest = new CreateProductPropertyRequest("Test Value", 1);

        when(productService.getByIdForProductPropertyManager(anyInt())).thenReturn(product);
        when(propertyService.getByIdForProductPropertyManager(anyInt())).thenReturn(property);
        when(productPropertyRepository.existsByProductIdAndPropertyId(anyInt(), anyInt())).thenReturn(true);
        when(messageService.getMessage(Messages.ProductPropertyMessages.ALREADY_EXISTS)).thenReturn("Product property already exists");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productPropertyManager.add(1, createProductPropertyRequest);
        });

        assertEquals("Product property already exists", exception.getMessage());
    }

    @Test
    void add_shouldAddProductPropertySuccessfully() {

        CreateProductPropertyRequest createProductPropertyRequest = new CreateProductPropertyRequest("Test Value", 1);

        when(productService.getByIdForProductPropertyManager(anyInt())).thenReturn(product);
        when(propertyService.getByIdForProductPropertyManager(anyInt())).thenReturn(property);
        when(productPropertyRepository.existsByProductIdAndPropertyId(anyInt(), anyInt())).thenReturn(false);

        when(productPropertyRepository.save(any())).thenReturn(productProperty);


        CreatedProductPropertyResponse result = productPropertyManager.add(1, createProductPropertyRequest);

        assertEquals(createProductPropertyRequest.propertyId(), result.propertyId());

    }

    @Test
    void getAll_ShouldReturnAllProductPropertiesSuccessfully() {

        ProductProperty productProperty1 = new ProductProperty();
        productProperty1.setId(1);

        ProductProperty productProperty2 = new ProductProperty();
        productProperty2.setId(2);

        List<ProductProperty> productPropertyList = Arrays.asList(productProperty1, productProperty2);
        when(productPropertyRepository.findAll()).thenReturn(productPropertyList);

        GetAllProductPropertyResponse response1 = new GetAllProductPropertyResponse(1, 1, 1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Value");
        GetAllProductPropertyResponse response2 = new GetAllProductPropertyResponse(2, 1, 1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Value 2");
        List<GetAllProductPropertyResponse> getAllProductPropertyResponseList = Arrays.asList(response1, response2);

        List<GetAllProductPropertyResponse> result = productPropertyManager.getAll();


        assertEquals(2, result.size());
        assertEquals(1, result.get(0).id());
        assertEquals(2, result.get(1).id());
    }

    @Test
    void getAllByProductId_ShouldReturnAllProductPropertiesSuccessfully() {

        int productId = 1;
        ProductProperty productProperty1 = new ProductProperty();
        productProperty1.setProduct(new Product(productId));

        ProductProperty productProperty2 = new ProductProperty();
        productProperty2.setProduct(new Product(productId));

        List<ProductProperty> productPropertyList = Arrays.asList(productProperty1, productProperty2);

        when(productPropertyRepository.findAllByProductId(anyInt())).thenReturn(productPropertyList);

        List<GetAllProductPropertiesByProductIdResponse> result = productPropertyManager.getAllByProductId(productId);

        assertEquals(2, result.size());
        assertEquals(result.get(0).productId(), result.get(1).productId());
    }

    @Test
    void getById_shouldThrowExceptionWhenProductPropertyNotFound() {

        int productId = 1;
        int propertyId = 2;

        when(productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId)).thenReturn(Optional.empty());
        when(messageService.getMessage(anyString())).thenReturn("Product property not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productPropertyManager.getById(productId, propertyId);
        });

        assertEquals("Product property not found", exception.getMessage());
    }

    @Test
    void getById_ShouldReturnProductPropertySuccessfully() {

        int productId = 1;
        int propertyId = 1;

        when(productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId)).thenReturn(Optional.of(productProperty));


        GetByIdProductPropertyResponse result = productPropertyManager.getById(productId, propertyId);


        assertEquals(productId, result.productId());
        assertEquals(propertyId, result.propertyId());
    }

    @Test
    void update_shouldThrowExceptionWhenProductPropertyNotFound() {

        UpdateProductPropertyRequest updateProductPropertyRequest = new UpdateProductPropertyRequest("Test Value");


        when(productPropertyRepository.findByProductIdAndPropertyId(anyInt(), anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(anyString())).thenReturn("Product property not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productPropertyManager.update(anyInt(), anyInt(), updateProductPropertyRequest);
        });

        assertEquals("Product property not found", exception.getMessage());

    }

    @Test
    void update_ShouldUpdateProductPropertySuccessfully() {

        int productId = 1;
        int propertyId = 1;

        UpdateProductPropertyRequest updateProductPropertyRequest = new UpdateProductPropertyRequest("Test Value");

        when(productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId)).thenReturn(Optional.of(productProperty));
        when(productPropertyRepository.save(any())).thenReturn(productProperty);

        UpdatedProductPropertyResponse result = productPropertyManager.update(productId, propertyId, updateProductPropertyRequest);

        assertEquals(productId, result.productId());
        assertEquals(propertyId, result.propertyId());
        assertEquals(updateProductPropertyRequest.value(), result.value());
    }

    @Test
    void delete_shouldThrowExceptionWhenProductPropertyNotFound() {

        when(productPropertyRepository.findByProductIdAndPropertyId(anyInt(), anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(anyString())).thenReturn("Product property not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productPropertyManager.delete(anyInt(), anyInt());
        });

        assertEquals("Product property not found", exception.getMessage());

    }

    @Test
    void delete_shouldDeleteProductPropertySuccessfully() {

        when(productPropertyRepository.findByProductIdAndPropertyId(anyInt(), anyInt())).thenReturn(Optional.of(productProperty));
        when(productPropertyRepository.save(any())).thenReturn(productProperty);


        DeletedProductPropertyResponse result = productPropertyManager.delete(anyInt(), anyInt());

        assertNotNull(productProperty.getDeletedDate());
        assertEquals(productProperty.getId(), result.id());
        assertEquals(productProperty.getProperty().getId(), result.propertyId());
        assertEquals(productProperty.getProduct().getId(), result.productId());

    }
}