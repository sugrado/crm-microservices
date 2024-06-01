package com.turkcell.crm.catalog_service.business.abstracts;


import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.requests.property.UpdatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.*;
import com.turkcell.crm.catalog_service.entities.concretes.Property;

import java.util.List;

public interface PropertyService {
    CreatedPropertyResponse add(CreatePropertyRequest request);

    List<GetAllPropertiesByCategoryIdResponse> getAllByCategoryId(int categoryId);

    GetByIdPropertyResponse getById(int id);

    List<GetAllPropertiesResponse> getAll();

    DeletePropertyResponse delete(int id);

    UpdatedPropertyResponse update(int id, UpdatePropertyRequest updatePropertyRequest);

    Property getByIdForProductPropertyManager(int id);
}
