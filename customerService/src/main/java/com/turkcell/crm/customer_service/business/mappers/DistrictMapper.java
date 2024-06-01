package com.turkcell.crm.customer_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetAllDistrictsResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.districts.GetByIdDistrictResponse;
import com.turkcell.crm.customer_service.entities.concretes.District;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface DistrictMapper {
    List<GetAllDistrictsResponse> toGetAllDistrictsResponseList(List<District> districtList);

    GetByIdDistrictResponse toGetByIdResponse(District district);
}
