package com.turkcell.crm.catalog_service.business.concretes;

import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetByIdPropertyResponse;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyManager implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyBusinessRules propertyBusinessRules;

    //todo: Yusuf anısına...
//    @Override
//    public List<CreatedPropertyResponse> add(List<CreatePropertyRequest> request) {
//
//        List<Property> propertyList=request.stream().map(x-> {
//                    Property property = this.propertyMapper.toProperty(x);
//                    Category category = this.categoryService.getByIdForPropertyService(x.categoryId());
//                    property.setCategory(category);
//                    return property;
//                }).toList();
//
//        List <Property> createdProperties = this.propertyRepository.saveAll(propertyList);
//
//        List<CreatedPropertyResponse> createdPropertyResponseList = createdProperties.stream().map(x->{
//            CreatedPropertyResponse createdPropertyResponse = this.propertyMapper.toCreatedPropertyResponse(x);
//            createdPropertyResponse.setCategoryName(x.getCategory().getName());
//            return createdPropertyResponse;
//        }).toList();
//        return createdPropertyResponseList;
//    }

    @Override
    public CreatedPropertyResponse add(CreatePropertyRequest request) {

        Property property = this.propertyMapper.toProperty(request);

        Property createdProperty = this.propertyRepository.save(property);

        return this.propertyMapper.toCreatedPropertyResponse(createdProperty);
    }

    @Override
    public List<GetAllPropertiesResponse> getAll() {
        List<Property> propertyList = this.propertyRepository.findAll();
        return this.propertyMapper.toGetAllPropertiesResponse(propertyList);
    }

    @Override
    public List<GetAllPropertiesByCategoryIdResponse> getAllByCategoryId(int categoryId) {

        List<Property> propertyList = this.propertyRepository.findAllByCategoryId(categoryId);
        return this.propertyMapper.toGetAllPropertiesByCategoryIdResponse(propertyList);
    }

    @Override
    public GetByIdPropertyResponse getById(int id) {
        Optional<Property> propertyOptional = this.propertyRepository.findById(id);
        this.propertyBusinessRules.propertyShouldBeExist(propertyOptional);
        return this.propertyMapper.toGetByIdPropertyResponse(propertyOptional.get());
    }
}
