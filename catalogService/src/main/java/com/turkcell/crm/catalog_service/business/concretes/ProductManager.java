package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
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

    @Override
    @Transactional
    public CreatedProductResponse add(CreateProductRequest request) {

        Product product = this.productMapper.toProduct(request);
        Product createdProduct = this.productRepository.save(product);

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
        return this.productMapper.toGetByIdProductResponse(optionalProduct.get());
    }

    @Override
    public UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        this.productBusinessRules.productShouldBeExist(optionalProduct);
        Product product = optionalProduct.get();

        this.productMapper.updateProductFromRequest(updateProductRequest, product);
        Product updatedProduct = this.productRepository.save(product);

        return this.productMapper.toUpdatedProductResponse(updatedProduct);
    }

    @Override
    public DeletedProductResponse delete(int id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        this.productBusinessRules.productShouldBeExist(optionalProduct);

        Product productToDelete = optionalProduct.get();
        productToDelete.setDeletedDate(LocalDateTime.now());
        Product deletedProduct = this.productRepository.save(productToDelete);

        return this.productMapper.toDeletedProductResponse(deletedProduct);
    }
}
