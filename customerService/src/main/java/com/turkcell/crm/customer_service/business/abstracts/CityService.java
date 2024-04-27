package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;

import java.util.List;

public interface CityService {
    List<GetAllCitiesResponse> getAll();

    List<Integer> getAllById(List<Integer> ids);
}
