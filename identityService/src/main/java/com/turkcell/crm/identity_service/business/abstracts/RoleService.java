package com.turkcell.crm.identity_service.business.abstracts;

import com.turkcell.crm.identity_service.business.dtos.responses.roles.GetByIdRoleResponse;

public interface RoleService {
    GetByIdRoleResponse getById(int id);
}
