package com.turkcell.crm.identityService.business.mappers;

import com.turkcell.crm.identityService.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.crm.identityService.core.utilities.mapping.MapstructService;
import com.turkcell.crm.identityService.entities.concretes.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface AuthMapper {
    User toUser(RegisterRequest registerRequest);
}
