package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.ProductPropertyDto;
import com.turkcell.crm.catalog_service.business.mappers.CategoryMapper;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapperImpl;
import com.turkcell.crm.catalog_service.business.rules.CategoryBusinessRules;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.kafka.producers.ProductProducer;
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

class ProductManagerTest {
    private ProductRepository productRepository;
    private MessageService messageService;
    private ProductManager productManager;

    private Product product;
    private ProductProducer productProducer;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CategoryBusinessRules categoryBusinessRules;
    private Category category;


    @BeforeEach()
    void setUp() {

        ProductMapper productMapper = new ProductMapperImpl();
        productRepository = mock(ProductRepository.class);
        messageService = mock(MessageService.class);
        ProductBusinessRules productBusinessRules = new ProductBusinessRules(messageService, productRepository);
        productProducer = mock(ProductProducer.class);

        /*categoryMapper = new CategoryMapperImpl();
        categoryRepository = mock(CategoryRepository.class);
        categoryBusinessRules = new CategoryBusinessRules(categoryRepository,messageService);
        categoryService = new CategoryManager(categoryRepository,categoryMapper,categoryBusinessRules);*/

        categoryService = mock(CategoryService.class);

        productManager = new ProductManager(productRepository, productMapper, productBusinessRules, productProducer, categoryService);

        category = new Category();
        category.setId(1);

        product = new Product();
        product.setId(1);
        product.setTitle("Test Title");
        product.setDescription("Test Description");
        product.setPrice(10000);
        product.setUnitsInStock(10);
        product.setCategory(category);

    }

    @Test
    void add_withValidRequest_shouldCreateAndReturnProduct() {

        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        CreatedProductResponse createdProductResponse = new CreatedProductResponse(1,
                LocalDateTime.now(), "Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdForProductManager(anyInt())).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        CreatedProductResponse result = productManager.add(createProductRequest);

        assertEquals(createdProductResponse.id(), result.id());
        assertEquals(createdProductResponse.categoryId(), result.categoryId());
    }

    @Test
    void add_withNonExistentCategory_shouldThrowBusinessException() {

        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdForProductManager(anyInt())).thenThrow(new BusinessException("Category not found"));
        assertThrows(BusinessException.class, () -> productManager.add(createProductRequest));

    }

    @Test
    void add_withDeletedCategory_shouldThrowBusinessException() {
        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdForProductManager(anyInt())).thenThrow(new BusinessException("Category is deleted"));

        assertThrows(BusinessException.class, () -> productManager.add(createProductRequest));
    }

    @Test
    void getAll_shouldReturnListOfProducts() {

        Product product1 = new Product();
        product1.setId(1);
        product1.setTitle("Test Title");

        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Test Title 2");

        List<Product> productList = Arrays.asList(product1, product2);

        GetAllProductsResponse response1 = new GetAllProductsResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title",
                "Test Description", 10000, 10, 1);

        GetAllProductsResponse response2 = new GetAllProductsResponse(2, LocalDateTime.now(), LocalDateTime.now(), "Test Title 2",
                "Test Description 2", 20000, 20, 1);
        List<GetAllProductsResponse> getAllProductsResponseList = Arrays.asList(response1, response2);

        when(productRepository.findAll()).thenReturn(productList);

        List<GetAllProductsResponse> result = productManager.getAll();

        assertEquals(getAllProductsResponseList.get(0).title(), result.get(0).title());
        assertEquals(getAllProductsResponseList.get(1).title(), result.get(1).title());
    }

    @Test
    void getById_withNonExistentId_shouldThrowBusinessException() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> productManager.getById(1));
        assertEquals("Product not found", exception.getMessage());
    }


    @Test
    void getById_withDeletedProduct_shouldThrowBusinessException() {

        product.setDeletedDate(LocalDateTime.now());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(messageService.getMessage(Messages.ProductMessages.DELETED)).thenReturn("Product is deleted");

        BusinessException exception = assertThrows(BusinessException.class, () -> productManager.getById(1));
        assertEquals("Product is deleted", exception.getMessage());
    }

    @Test
    void getById_withValidId_shouldReturnProduct() {
        ProductPropertyDto productPropertyDto = new ProductPropertyDto("Test Value", "Test Property");
        GetByIdProductResponse getByIdProductResponse = new GetByIdProductResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 15000, 5,
                List.of(productPropertyDto));

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        GetByIdProductResponse result = productManager.getById(1);

        assertEquals(getByIdProductResponse.id(), result.id());
        assertEquals(getByIdProductResponse.title(), result.title());
    }

    @Test
    void testUpdate_Success() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Test Title", "Test Description", 30000, 30);
        UpdatedProductResponse updatedProductResponse = new UpdatedProductResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 30000, 30);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //doNothing().when(productMapper).updateProductFromRequest(updateProductRequest, product);
        //doNothing().when(productProducer).send(any(ProductProducer.class));

        UpdatedProductResponse response = productManager.update(1, updateProductRequest);

        assertEquals(updatedProductResponse.id(), response.id());
        assertEquals(updatedProductResponse.title(), response.title());

    }

    @Test
    void testUpdate_ProductNotFound() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Test Title", "Test Description", 30000, 30);
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.update(1, updateProductRequest);
        });

        assertEquals("Product not found", exception.getMessage());

    }

    @Test
    void testUpdate_ProductDeleted() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Test Title", "Test Description", 30000, 30);
        product.setDeletedDate(LocalDateTime.now());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(messageService.getMessage(Messages.ProductMessages.DELETED)).thenReturn("Product is deleted");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.update(1, updateProductRequest);
        });

        assertEquals("Product is deleted", exception.getMessage());
    }

    @Test
    void testDelete_ProductNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.delete(1);
        });

        assertEquals("Product not found", exception.getMessage());

    }

    @Test
    void testDelete_ProductDeleted() {
        product.setDeletedDate(LocalDateTime.now());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(messageService.getMessage(Messages.ProductMessages.DELETED)).thenReturn("Product is already deleted");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.delete(1);
        });

        assertEquals("Product is already deleted", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        ProductPropertyDto productPropertyDto = new ProductPropertyDto("Test Value", "Test Property");
        DeletedProductResponse deletedProductResponse = new DeletedProductResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 10000,
                5, List.of(productPropertyDto));

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //doNothing().when(productProducer).send(any());

        DeletedProductResponse response = productManager.delete(1);

        assertEquals(deletedProductResponse.id(), response.id());
        assertEquals(deletedProductResponse.description(), response.description());
    }

    @Test
    void testGetAllByCategoryId_Success() {
        Product product1 = new Product();
        product1.setCategory(new Category(1));
        product1.setTitle("Test Title");

        Product product2 = new Product();
        product2.setCategory(new Category(1));
        product2.setTitle("Test Title 2");
        List<Product> productList = Arrays.asList(product1, product2);

        GetAllProductsByCategoryIdResponse response = new GetAllProductsByCategoryIdResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 30000, 30, 1);

        GetAllProductsByCategoryIdResponse response2 = new GetAllProductsByCategoryIdResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title 2", "Test Description", 30000, 30, 1);

        List<GetAllProductsByCategoryIdResponse> categoryIdResponseList = Arrays.asList(response, response2);

        when(productRepository.findAllByCategoryId(anyInt())).thenReturn(productList);


        List<GetAllProductsByCategoryIdResponse> result = productManager.getAllByCategoryId(1);


        assertEquals(2, result.size());
        assertEquals(categoryIdResponseList.get(0).categoryId(), result.get(0).categoryId());
        assertEquals(categoryIdResponseList.get(1).categoryId(), result.get(1).categoryId());
        assertEquals(categoryIdResponseList.get(0).title(), result.get(0).title());
        assertEquals(categoryIdResponseList.get(1).title(), result.get(1).title());
    }

    @Test
    void testGetByIdForProductPropertyManager_Success() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        Product result = productManager.getByIdForProductPropertyManager(1);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getTitle(), result.getTitle());
    }

    @Test
    void testGetByIdForProductPropertyManager_ProductNotFound() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.getByIdForProductPropertyManager(1);
        });

        assertEquals("Product not found", exception.getMessage());

    }

    @Test
    void testGetByIdForProductPropertyManager_ProductDeleted() {

        product.setDeletedDate(LocalDateTime.now());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(messageService.getMessage(Messages.ProductMessages.DELETED)).thenReturn("Product is already deleted");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.getByIdForProductPropertyManager(1);
        });

        assertEquals("Product is already deleted", exception.getMessage());
    }
}