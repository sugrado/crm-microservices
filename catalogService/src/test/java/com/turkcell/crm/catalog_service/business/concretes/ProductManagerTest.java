package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.constants.Messages;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.mappers.CategoryMapper;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import com.turkcell.crm.common.shared.kafka.events.orders.OrderCreatedEventProduct;
import org.mapstruct.factory.Mappers;
import com.turkcell.crm.catalog_service.business.rules.CategoryBusinessRules;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.kafka.producers.ProductProducer;
import com.turkcell.crm.common.shared.dtos.catalogs.GetByIdProductResponse;
import com.turkcell.crm.common.shared.dtos.catalogs.ProductPropertyDto;
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
    private CategoryService categoryService;
    private Category category;

    @BeforeEach()
    void setUp() {

        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        productRepository = mock(ProductRepository.class);
        messageService = mock(MessageService.class);
        ProductBusinessRules productBusinessRules = new ProductBusinessRules(messageService, productRepository);
        ProductProducer productProducer = mock(ProductProducer.class);


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
    void add_shouldAddProductSuccessfully() {

        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        CreatedProductResponse createdProductResponse = new CreatedProductResponse(1,
                LocalDateTime.now(), "Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdEntity(anyInt())).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        CreatedProductResponse result = productManager.add(createProductRequest);

        assertEquals(createdProductResponse.id(), result.id());
        assertEquals(createdProductResponse.categoryId(), result.categoryId());
    }

    @Test
    void add_withNonExistentCategory_shouldThrowBusinessException() {

        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdEntity(anyInt())).thenThrow(new BusinessException("Category not found"));
        assertThrows(BusinessException.class, () -> productManager.add(createProductRequest));

    }

    @Test
    void add_withDeletedCategory_shouldThrowBusinessException() {

        CreateProductRequest createProductRequest = new CreateProductRequest("Test Title",
                "Test Description", 10000, 10, 1);

        when(categoryService.getByIdEntity(anyInt())).thenThrow(new BusinessException("Category is deleted"));

        assertThrows(BusinessException.class, () -> productManager.add(createProductRequest));
    }

    @Test
    void getAll_shouldReturnListOfProductsSuccessfully() {

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
    void getById_shouldThrowBusinessExceptionWhenProductNotFound() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> productManager.getById(1));
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void getById_shouldReturnProductSuccessfully() {

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
    void update_shouldUpdateProductSuccessfully() {

        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Test Title", "Test Description", 30000, 30,1);
        UpdatedProductResponse updatedProductResponse = new UpdatedProductResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 30000, 30,1);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        UpdatedProductResponse response = productManager.update(1, updateProductRequest);

        assertEquals(updatedProductResponse.id(), response.id());
        assertEquals(updatedProductResponse.title(), response.title());

    }

    @Test
    void update_shouldThrowBusinessExceptionWhenProductDoesNotExist() {

        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Test Title", "Test Description", 30000, 30,1);
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.update(1, updateProductRequest);
        });

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowBusinessExceptionWhenProductDoesNotExist() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.delete(1);
        });

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void delete_shouldDeleteProductSuccessfully() {

        ProductPropertyDto productPropertyDto = new ProductPropertyDto("Test Value", "Test Property");
        DeletedProductResponse deletedProductResponse = new DeletedProductResponse(1,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "Test Title", "Test Description", 10000,
                5, List.of(productPropertyDto));

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        DeletedProductResponse response = productManager.delete(1);

        assertEquals(deletedProductResponse.id(), response.id());
        assertEquals(deletedProductResponse.description(), response.description());
    }

    @Test
    void getAllByCategoryId_shouldReturnAllProductsSuccessfully() {

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
    void getByIdForProductPropertyManager_ShouldReturnProductSuccessfully() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        Product result = productManager.getByIdForProductPropertyManager(1);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getTitle(), result.getTitle());
    }

    @Test
    void getByIdForProductPropertyManager_shouldThrowBusinessExceptionWhenProductNotFound() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(messageService.getMessage(Messages.ProductMessages.NOT_FOUND)).thenReturn("Product not found");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productManager.getByIdForProductPropertyManager(1);
        });

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void getAllForCompleteOrder_shouldReturnAllForCompleteOrderResponsesSuccessfully() {

        Product product1 = new Product();
        product1.setId(1);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        List<Integer> productIdList = Arrays.asList(1, 2);

        GetAllForCompleteOrderResponse response1 = new GetAllForCompleteOrderResponse(1, "Product 1",1000);
        GetAllForCompleteOrderResponse response2 = new GetAllForCompleteOrderResponse(2, "Product 2",2000);

        List<GetAllForCompleteOrderResponse> getAllForCompleteOrderResponseList = Arrays.asList(response1, response2);

        when(productRepository.findAllByIdIsIn(anyList())).thenReturn(productList);


        List<GetAllForCompleteOrderResponse> response = productManager.getAllForCompleteOrder(productIdList);

        assertEquals(getAllForCompleteOrderResponseList.size(), response.size());
        assertEquals(getAllForCompleteOrderResponseList.get(0).id(), response.get(0).id());
        assertEquals(getAllForCompleteOrderResponseList.get(0).title(), response.get(0).title());
        assertEquals(getAllForCompleteOrderResponseList.get(1).id(), response.get(1).id());
        assertEquals(getAllForCompleteOrderResponseList.get(1).title(), response.get(1).title());
    }
    @Test
    void decreaseStocks_shouldDecreaseStockForEachProductSuccessfully() {

        Product product1 = new Product();
        product1.setId(1);
        product1.setUnitsInStock(10);

        Product product2 = new Product();
        product2.setId(2);
        product2.setUnitsInStock(20);

        List<Product> productList = Arrays.asList(product1, product2);

        OrderCreatedEventProduct eventProduct1 = new OrderCreatedEventProduct(1,"Test Title",1000);
        OrderCreatedEventProduct eventProduct2 = new OrderCreatedEventProduct(2, "Test title 2", 2000);
        List<OrderCreatedEventProduct> eventProducts = Arrays.asList(eventProduct1, eventProduct2);


        when(productRepository.findAllByIdIsIn(anyList())).thenReturn(productList);

        productManager.decreaseStocks(eventProducts);

        assertEquals(9, productList.get(0).getUnitsInStock());
        assertEquals(19, productList.get(1).getUnitsInStock());
    }
}