package com.turkcell.crm.identity_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.identity_service.business.dtos.responses.roles.GetByIdRoleResponse;
import com.turkcell.crm.identity_service.entities.concretes.Role;
import org.mapstruct.Mapper;

@Mapper(config = MapstructService.class)
public interface RoleMapper {
    GetByIdRoleResponse toGetByIdRoleResponse(Role role);
}
