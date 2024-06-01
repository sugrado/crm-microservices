package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.identity_service.business.abstracts.RoleService;
import com.turkcell.crm.identity_service.business.dtos.responses.roles.GetByIdRoleResponse;
import com.turkcell.crm.identity_service.business.mappers.RoleMapper;
import com.turkcell.crm.identity_service.business.rules.RoleBusinessRules;
import com.turkcell.crm.identity_service.data_access.abstracts.RoleRepository;
import com.turkcell.crm.identity_service.entities.concretes.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {
    private final RoleBusinessRules roleBusinessRules;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public GetByIdRoleResponse getById(int id) {
        Optional<Role> roleOptional = this.roleRepository.findById(id);
        this.roleBusinessRules.roleShouldBeExists(roleOptional);
        return this.roleMapper.toGetByIdRoleResponse(roleOptional.get());
    }
}
