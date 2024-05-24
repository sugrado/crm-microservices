package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetByIdCityResponse;
import com.turkcell.crm.customer_service.business.mappers.CityMapper;
import com.turkcell.crm.customer_service.business.rules.CityBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.CityRepository;
import com.turkcell.crm.customer_service.entities.concretes.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityManager implements CityService {

    private final CityRepository cityRepository;
    private final CityBusinessRules cityBusinessRules;
    private final CityMapper cityMapper;

    @Override
    public List<GetAllCitiesResponse> getAll() {

        return this.cityMapper.toGetAllCitiesResponseList(this.cityRepository.findAll());
    }

    @Override
    public List<City> getAllById(List<Integer> ids) {

        return this.cityRepository.findAllByIdIsIn(ids);
    }

    @Override
    public GetByIdCityResponse getById(int id) {
        Optional<City> cityOptional = this.cityRepository.findById(id);
        this.cityBusinessRules.cityShouldBeExist(cityOptional);

        return this.cityMapper.toGetByIdResponse(cityOptional.get());
    }
}
