package com.turkcell.crm.identity_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.identity_service.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AuthMapper {
    User toUser(RegisterRequest registerRequest);
}
