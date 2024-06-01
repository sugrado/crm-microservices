package com.turkcell.crm.identity_service.business.abstracts;

import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.CreateUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.DeleteUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.CreatedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.DeletedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetUserRolesByUserResponse;

import java.util.List;

public interface UserRoleService {
    List<GetUserRolesByUserResponse> getAllByUserId(int userId);

    CreatedUserRoleResponse addRoleToUser(int userId, CreateUserRoleRequest createUserRoleRequest);

    DeletedUserRoleResponse deleteRoleFromUser(int userId, DeleteUserRoleRequest deleteUserRoleRequest);

    void clearUserRoles(int userId);
}
