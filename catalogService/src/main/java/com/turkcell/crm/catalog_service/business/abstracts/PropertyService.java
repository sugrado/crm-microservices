package com.turkcell.crm.catalog_service.business.abstracts;


import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.CreatedPropertyResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesByCategoryIdResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetAllPropertiesResponse;
import com.turkcell.crm.catalog_service.business.dtos.responses.property.GetByIdPropertyResponse;

import java.util.List;

public interface PropertyService {
    CreatedPropertyResponse add(CreatePropertyRequest request);

    List<GetAllPropertiesByCategoryIdResponse> getAllByCategoryId(int categoryId);

    GetByIdPropertyResponse getById(int id);

    List<GetAllPropertiesResponse> getAll();
}
