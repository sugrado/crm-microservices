package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.ProductPropertyService;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.CreateProductPropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.productProperty.ProductPropertyDto;
import com.turkcell.crm.catalog_service.business.mappers.ProductPropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.ProductPropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Product;
import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPropertyManager implements ProductPropertyService {
    private final ProductPropertyRepository productPropertyRepository;
    private final ProductPropertyMapper productPropertyMapper;
    private final PropertyService propertyService;
    private final ProductBusinessRules productBusinessRules;
    private final PropertyBusinessRules propertyBusinessRules;

    @Override
    public void add(int productId, CreateProductPropertyRequest request) {
        productBusinessRules.productIdShouldBeExist(productId);
        propertyBusinessRules.propertyIdShouldBeExist(request.propertyId());
        ProductProperty productPropertyToSave =productPropertyMapper.toProductProperty(request);
        productPropertyToSave.setProduct(new Product(productId));
        //productPropertyToSave.setProperty(new Property(request.propertyId()));
        productPropertyRepository.save(productPropertyToSave);
    }
    //TODO:Database'e çift kayıt atıyor.ilk atadıklarının foreign keyleri nulll geliyor.
    @Override
    public void add (List<ProductPropertyDto> productPropertyDtoList, Product product) {
        List<Integer> validatedProperties = propertyService
                .getAllById(productPropertyDtoList.stream().map(ProductPropertyDto::propertyId).toList())
                .stream()
                .map(Property::getId)
                .toList();
        List<ProductProperty> productPropertiesToCreate = productPropertyDtoList
                .stream()
                .filter(a -> validatedProperties.contains(a.propertyId()))
                .map(x -> {
                    ProductProperty productProperty = this.productPropertyMapper.toProductProperty(x);
                    productProperty.setProduct(product);
                    return productProperty;
                })
                .toList();
        this.productPropertyRepository.saveAll(productPropertiesToCreate);
    }
}
