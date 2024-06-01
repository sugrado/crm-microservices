package com.turkcell.crm.identity_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetByIdUserResponse;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface UserMapper {
    GetByIdUserResponse toGetByIdUserResponse(User user);
}
