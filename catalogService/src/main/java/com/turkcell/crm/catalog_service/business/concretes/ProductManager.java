package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductPropertyService productPropertyService;
    @Override
    public CreatedProductResponse add(CreateProductRequest request) {

        Product product = this.productMapper.toProduct(request);
        Product createdProduct = this.productRepository.save(product);
        this.productPropertyService.add(request.properties(), product);

        return this.productMapper.toCreatedProductResponse(createdProduct);
    }

    @Override
    public List<GetAllProductsResponse> getAll() {
        return null;
    }

    @Override
    public GetByIdProductResponse getById(int id) {
        return null;
    }

    @Override
    public UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public DeletedProductResponse delete(int id) {
        return null;
    }
}
