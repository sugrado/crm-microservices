package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import com.turkcell.crm.catalog_service.kafka.producers.ProductProducer;
import com.turkcell.crm.common.kafka.events.ProductCreatedEvent;
import com.turkcell.crm.common.kafka.events.ProductDeletedEvent;
import com.turkcell.crm.common.kafka.events.ProductUpdatedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductBusinessRules productBusinessRules;
    private final ProductProducer productProducer;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public CreatedProductResponse add(CreateProductRequest request) {

        Product product = this.productMapper.toProduct(request);
        product.setCategory(categoryService.getByIdForProductManager(request.categoryId()));
        Product createdProduct = this.productRepository.save(product);

        ProductCreatedEvent productCreatedEvent = productMapper.toProductCreatedEvent(createdProduct);
        productProducer.send(productCreatedEvent);

        return this.productMapper.toCreatedProductResponse(createdProduct);
    }

    @Override
    public List<GetAllProductsResponse> getAll() {

        List<Product> productList = this.productRepository.findAll();

        return this.productMapper.toGetAllProductsResponse(productList);
    }

    @Override
    public GetByIdProductResponse getById(int id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        this.productBusinessRules.productShouldBeExist(optionalProduct);
        this.productBusinessRules.productShouldNotBeDeleted(optionalProduct);

        return this.productMapper.toGetByIdProductResponse(optionalProduct.get());
    }

    @Override
    @Transactional
    public UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        this.productBusinessRules.productShouldBeExist(optionalProduct);
        this.productBusinessRules.productShouldNotBeDeleted(optionalProduct);

        Product product = optionalProduct.get();

        this.productMapper.updateProductFromRequest(updateProductRequest, product);
        Product updatedProduct = this.productRepository.save(product);

        ProductUpdatedEvent productUpdatedEvent = productMapper.toProductUpdatedEvent(updatedProduct);
        productProducer.send(productUpdatedEvent);

        return this.productMapper.toUpdatedProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public DeletedProductResponse delete(int id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        this.productBusinessRules.productShouldBeExist(optionalProduct);
        this.productBusinessRules.productShouldNotBeDeleted(optionalProduct);

        Product productToDelete = optionalProduct.get();
        productToDelete.setDeletedDate(LocalDateTime.now());
        Product deletedProduct = this.productRepository.save(productToDelete);

        ProductDeletedEvent productDeletedEvent = productMapper.toProductDeletedEvent(deletedProduct);
        productProducer.send(productDeletedEvent);

        return this.productMapper.toDeletedProductResponse(deletedProduct);
    }

    @Override
    public List<GetAllProductsByCategoryIdResponse> getAllByCategoryId(int categoryId) {

        List<Product> propertyList = this.productRepository.findAllByCategoryId(categoryId);

        return this.productMapper.toGetAllProductsByCategoryIdResponse(propertyList);
    }

    @Override
    public Product getByIdForProductPropertyManager(int id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        this.productBusinessRules.productShouldBeExist(optionalProduct);
        this.productBusinessRules.productShouldNotBeDeleted(optionalProduct);

        return optionalProduct.get();
    }
}
