package com.turkcell.crm.customer_service.business.abstracts;

import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsByCityResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetByIdDistrictResponse;
import com.turkcell.crm.customer_service.entities.concretes.District;

import java.util.List;

public interface DistrictService {
    List<GetAllDistrictsResponse> getAll();

    List<District> getAllById(List<Integer> ids);

    GetByIdDistrictResponse getById(int id);

    List<GetAllDistrictsByCityResponse> getAllByCityId(int cityId);
}
