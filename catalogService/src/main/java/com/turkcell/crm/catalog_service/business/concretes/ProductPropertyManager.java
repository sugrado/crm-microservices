package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.UpdateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.*;
import com.turkcell.crm.catalog_service.business.mappers.ProductPropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductPropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPropertyManager implements ProductPropertyService {

    private final ProductPropertyRepository productPropertyRepository;
    private final ProductPropertyMapper productPropertyMapper;
    private final ProductService productService;
    private final PropertyService propertyService;
    private final ProductPropertyBusinessRules productPropertyBusinessRules;

    @Override
    public CreatedProductPropertyResponse add(int productId, CreateProductPropertyRequest request) {

        Product product = this.productService.getByIdForProductPropertyManager(productId);
        Property property = this.propertyService.getByIdForProductPropertyManager(request.propertyId());
        this.productPropertyBusinessRules.productAndPropertyCategoryIdShouldBeMatch(product, property);
        this.productPropertyBusinessRules.productPropertyShouldBeUnique(productId, request.propertyId());

        ProductProperty productPropertyToSave = this.productPropertyMapper.toProductProperty(request);
        productPropertyToSave.setProduct(new Product(productId));

        ProductProperty productProperty = this.productPropertyRepository.save(productPropertyToSave);
        return this.productPropertyMapper.toCreatedProductPropertyResponse(productProperty);
    }

    @Override
    public List<GetAllProductPropertyResponse> getAll() {
        List<ProductProperty> productProperties = this.productPropertyRepository.findAll();

        return this.productPropertyMapper.toGetAllProductPropertyResponse(productProperties);
    }

    @Override
    public List<GetAllProductPropertiesByProductIdResponse> getAllByProductId(int productId) {

        List<ProductProperty> productPropertiesProductId = this.productPropertyRepository.findAllByProductId(productId);

        return this.productPropertyMapper.toGetAllProductPropertiesByProductIdResponse(productPropertiesProductId);
    }


    @Override
    public GetByIdProductPropertyResponse getById(int productId, int propertyId) {
        Optional<ProductProperty> optionalProductProperty = this.productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId);

        this.productPropertyBusinessRules.productPropertyShouldBeExists(optionalProductProperty);
        this.productPropertyBusinessRules.productPropertyShouldNotBeDeleted(optionalProductProperty);

        return this.productPropertyMapper.toGetByIdProductPropertyResponse(optionalProductProperty.get());
    }

    @Override
    public UpdatedProductPropertyResponse update(int productId, int propertyId, UpdateProductPropertyRequest updateProductPropertyRequest) {
        Optional<ProductProperty> optionalProductProperty = this.productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId);

        this.productPropertyBusinessRules.productPropertyShouldBeExists(optionalProductProperty);
        this.productPropertyBusinessRules.productPropertyShouldNotBeDeleted(optionalProductProperty);

        ProductProperty productProperty = optionalProductProperty.get();

        this.productPropertyMapper.updateProductPropertyFromRequest(updateProductPropertyRequest, productProperty);

        ProductProperty updatedProductProperty = this.productPropertyRepository.save(productProperty);

        return this.productPropertyMapper.toUpdatedProductPropertyResponse(updatedProductProperty);
    }

    @Override
    public DeletedProductPropertyResponse delete(int productId, int propertyId) {
        Optional<ProductProperty> optionalProductProperty = this.productPropertyRepository.findByProductIdAndPropertyId(productId, propertyId);

        this.productPropertyBusinessRules.productPropertyShouldBeExists(optionalProductProperty);
        this.productPropertyBusinessRules.productPropertyShouldNotBeDeleted(optionalProductProperty);

        ProductProperty productPropertyToDelete = optionalProductProperty.get();
        productPropertyToDelete.setDeletedDate(LocalDateTime.now());
        ProductProperty deletedProduct = this.productPropertyRepository.save(productPropertyToDelete);

        return this.productPropertyMapper.toDeletedProductPropertyResponse(deletedProduct);
    }
}
