package com.turkcell.crm.catalog_service.business.abstracts;


import com.turkcell.crm.catalog_service.business.dtos.requests.property.CreatePropertyRequest;
import com.turkcell.crm.catalog_service.entities.concretes.Property;

import java.util.List;

public interface PropertyService {
    List<Property> add(List<CreatePropertyRequest> request);
    List<Property> getAllById(List<Integer> ids);
}
