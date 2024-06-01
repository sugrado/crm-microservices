package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.abstracts.DistrictService;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsByCityResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetByIdDistrictResponse;
import com.turkcell.crm.customer_service.business.mappers.DistrictMapper;
import com.turkcell.crm.customer_service.business.rules.DistrictBusinessRules;
import com.turkcell.crm.customer_service.data_access.abstracts.DistrictRepository;
import com.turkcell.crm.customer_service.entities.concretes.District;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictManager implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictBusinessRules districtBusinessRules;
    private final DistrictMapper districtMapper;

    @Override
    public List<GetAllDistrictsResponse> getAll() {

        return this.districtMapper.toGetAllDistrictsResponseList(this.districtRepository.findAll());
    }

    @Override
    public List<District> getAllById(List<Integer> ids) {

        return this.districtRepository.findAllByIdIsIn(ids);
    }

    @Override
    public GetByIdDistrictResponse getById(int id) {
        Optional<District> districtOptional = this.districtRepository.findById(id);
        this.districtBusinessRules.districtShouldBeExist(districtOptional);

        return this.districtMapper.toGetByIdResponse(districtOptional.get());
    }

    @Override
    public List<GetAllDistrictsByCityResponse> getAllByCityId(int cityId) {
        return List.of();
    }
}
