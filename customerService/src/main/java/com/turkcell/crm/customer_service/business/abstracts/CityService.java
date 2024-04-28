package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.entities.concretes.City;

import java.util.List;

public interface CityService {
    List<GetAllCitiesResponse> getAll();

    List<City> getAllById(List<Integer> ids);
}
