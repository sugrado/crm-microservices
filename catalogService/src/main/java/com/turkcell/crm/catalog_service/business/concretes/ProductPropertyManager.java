package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.abstracts.ProductService;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.product_property.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.CreatedProductPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.product_property.DeletedProductPropertyResponse;
import com.turkcell.crm.catalog_service.business.mappers.ProductPropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductPropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        this.productService.getById(productId);
        this.propertyService.getById(request.propertyId());
        this.productPropertyBusinessRules.productPropertyShouldBeUnique(productId, request.propertyId());

        ProductProperty productPropertyToSave = this.productPropertyMapper.toProductProperty(request);
        productPropertyToSave.setProduct(new Product(productId));

        ProductProperty productProperty = this.productPropertyRepository.save(productPropertyToSave);
        return this.productPropertyMapper.toCreatedProductPropertyResponse(productProperty);
    }

    @Override
    public DeletedProductPropertyResponse delete(int id) {
        Optional<ProductProperty> optionalProduct = this.productPropertyRepository.findById(id);
        this.productPropertyBusinessRules.productPropertyShouldBeExists(optionalProduct);

        ProductProperty productPropertyToDelete = optionalProduct.get();
        productPropertyToDelete.setDeletedDate(LocalDateTime.now());
        ProductProperty deletedProduct = this.productPropertyRepository.save(productPropertyToDelete);

        return this.productPropertyMapper.toDeletedProductPropertyResponse(deletedProduct);
    }
}
