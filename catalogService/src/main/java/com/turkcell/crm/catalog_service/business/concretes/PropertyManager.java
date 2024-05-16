package com.turkcell.crm.catalog_service.business.concretes;
import com.turkcell.crm.catalog_service.business.abstracts.CategoryService;
import com.turkcell.crm.catalog_service.business.abstracts.PropertyService;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.business.mappers.PropertyMapper;
import com.turkcell.crm.catalog_service.business.rules.PropertyBusinessRules;
import com.turkcell.crm.catalog_service.data_access.abstracts.PropertyRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.catalog_service.entities.concretes.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Property> getAllById(List<Integer> ids){
        return this.propertyRepository.findAllByIdIsIn(ids);
    }

    @Override
    public List<GetAllPropertiesByCategoryIdResponse> getAll(int categoryId) {

        List<Property> propertyList = this.propertyRepository.findAllByCategoryId(categoryId);
        return this.propertyMapper.toGetAllPropertiesResponse(propertyList);
    }
}
