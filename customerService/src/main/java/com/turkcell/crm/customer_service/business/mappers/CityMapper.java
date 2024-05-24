package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetByIdCityResponse;
import com.turkcell.crm.customer_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.customer_service.entities.concretes.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CityMapper {
    List<GetAllCitiesResponse> toGetAllCitiesResponseList(List<City> cityList);

    GetByIdCityResponse toGetByIdResponse(City city);
}
