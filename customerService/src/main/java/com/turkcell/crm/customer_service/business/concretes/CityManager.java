package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.CityService;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.business.mappers.CityMapper;
import com.turkcell.crm.customer_service.data_access.abstracts.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityManager implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<GetAllCitiesResponse> getAll() {
        return this.cityMapper.toGetAllCitiesResponseList(this.cityRepository.findAll());
    }

    @Override
    public List<Integer> getAllById(List<Integer> ids) {
        return this.cityRepository.findAllByIdIsIn(ids);
    }
}
