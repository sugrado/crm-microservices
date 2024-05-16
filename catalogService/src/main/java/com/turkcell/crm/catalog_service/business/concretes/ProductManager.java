package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.CreateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product.UpdateProductRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product.*;
import com.turkcell.crm.catalog_service.business.mappers.ProductMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
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
    private final ProductPropertyService productPropertyService;
    private final ProductBusinessRules productBusinessRules;
    @Override
    @Transactional
    public CreatedProductResponse add(CreateProductRequest request) {

        Product product = this.productMapper.toProduct(request);
        Product createdProduct = this.productRepository.save(product);
        this.productPropertyService.add(request.properties(), createdProduct);

        return this.productMapper.toCreatedProductResponse(createdProduct);
    }

    @Override
    public List<GetAllProductsResponse> getAll() {

        List<Product> productList = this.productRepository.findAll();
        List<GetAllProductsResponse> getAllProductsResponseList = this.productMapper.toGetAllProductsResponse(productList);

        return getAllProductsResponseList;
    }
    //TODO: getByIdResponse içerisindeki dto nun içindeki propertyName null geliyor. delete de aynı şekilde. ayrıca deneme yaparken bir kaç dto yu class'a çektim haberiniz olsun-Yusuf
    @Override
    public GetByIdProductResponse getById(int id) {

        this.productBusinessRules.productIdShouldBeExist(id);
        Product product = this.productRepository.findById(id).get();
        List<String> productNames = product.getProperties().stream().map(x->x.getProperty().getName()).toList();
        GetByIdProductResponse getByIdProductResponse = this.productMapper.toGetByIdProductResponse(product);
        //getByIdProductResponse.propertiesDto().stream().map(x->{x.setPropertyName(productNames.stream().findAny().get());
        //return x;}).toList();
        //getByIdProductResponse.getPropertiesDto().stream().map(a->{a.setPropertyName(productNames.stream().map(s -> s.getBytes()).toString());
        //    return getByIdProductResponse;});
        return getByIdProductResponse;
    }

    @Override
    @Transactional
    public UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest) {

        this.productBusinessRules.productIdShouldBeExist(id);
        Product product= this.productRepository.findById(id).get();

        this.productMapper.updateProductFromRequest(updateProductRequest,product);
        this.productRepository.save(product);

        return this.productMapper.toUpdatedProductResponse(product);
    }

    @Override
    @Transactional
    public DeletedProductResponse delete(int id) {

        this.productBusinessRules.productIdShouldBeExist(id);

        Product productToDelete = this.productRepository.findById(id).get();
        productToDelete.setDeletedDate(LocalDateTime.now());
        Product deletedProduct = this.productRepository.save(productToDelete);

        return this.productMapper.toDeletedProductResponse(deletedProduct);
    }
}
