package com.turkcell.crm.identity_service.business.dtos.responses.user_roles;

public record DeletedUserRoleResponse(
        int id,
        int userId,
        int roleId
) {
}
