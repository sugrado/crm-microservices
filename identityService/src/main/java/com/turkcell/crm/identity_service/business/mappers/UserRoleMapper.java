package com.turkcell.crm.identity_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.CreateUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.CreatedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.DeletedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetUserRolesByUserResponse;
import com.turkcell.crm.identity_service.entities.concretes.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface UserRoleMapper {
    @Mapping(target = "roleId", source = "role.id")
    GetUserRolesByUserResponse toGetUserRolesByUserResponse(UserRole userRole);

    List<GetUserRolesByUserResponse> toGetUserRolesByUserResponses(List<UserRole> userRoles);

    @Mapping(target = "role.id", source = "roleId")
    UserRole toUserRole(CreateUserRoleRequest createUserRoleRequest);

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "userId", source = "user.id")
    CreatedUserRoleResponse toCreatedUserRoleResponse(UserRole userRole);

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "userId", source = "user.id")
    DeletedUserRoleResponse toDeletedUserRoleResponse(UserRole deletedUserRole);
}
