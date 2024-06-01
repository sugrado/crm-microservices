package com.turkcell.crm.identity_service.business.dtos.responses.user_roles;

public record CreatedUserRoleResponse(
        int id,
        int userId,
        int roleId
) {
}
