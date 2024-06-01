package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.identity_service.business.abstracts.RoleService;
import com.turkcell.crm.identity_service.business.abstracts.UserRoleCacheService;
import com.turkcell.crm.identity_service.business.abstracts.UserRoleService;
import com.turkcell.crm.identity_service.business.abstracts.UserService;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.CreateUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.DeleteUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.roles.GetByIdRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.CreatedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.DeletedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetByIdUserResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetUserRolesByUserResponse;
import com.turkcell.crm.identity_service.business.mappers.UserRoleMapper;
import com.turkcell.crm.identity_service.business.rules.UserRoleBusinessRules;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRoleRepository;
import com.turkcell.crm.identity_service.entities.concretes.User;
import com.turkcell.crm.identity_service.entities.concretes.UserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleManager implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRoleBusinessRules userRoleBusinessRules;
    private final UserRoleCacheService userRoleCacheService;

    @Override
    public List<GetUserRolesByUserResponse> getAllByUserId(int userId) {
        this.userService.getById(userId);
        List<UserRole> userRoles = this.userRoleRepository.findAllByUserId(userId);
        return this.userRoleMapper.toGetUserRolesByUserResponses(userRoles);
    }

    @Override
    @Transactional
    public CreatedUserRoleResponse addRoleToUser(int userId, CreateUserRoleRequest createUserRoleRequest) {
        GetByIdUserResponse user = this.userService.getById(userId);
        GetByIdRoleResponse role = this.roleService.getById(createUserRoleRequest.roleId());

        this.userRoleBusinessRules.userShouldNotHaveRole(user.id(), role.id());

        UserRole userRole = this.userRoleMapper.toUserRole(createUserRoleRequest);
        userRole.setUser(new User(user.id()));
        userRole = this.userRoleRepository.save(userRole);

        this.userRoleCacheService.addToUser(user.email(), role.name());

        return this.userRoleMapper.toCreatedUserRoleResponse(userRole);
    }

    @Override
    @Transactional
    public DeletedUserRoleResponse deleteRoleFromUser(int userId, DeleteUserRoleRequest deleteUserRoleRequest) {
        GetByIdUserResponse user = this.userService.getById(userId);
        GetByIdRoleResponse role = this.roleService.getById(deleteUserRoleRequest.roleId());

        Optional<UserRole> userRoleOptional = this.userRoleRepository.findByUserIdAndRoleId(user.id(), role.id());
        this.userRoleBusinessRules.userShouldHaveRole(userRoleOptional);
        UserRole userRole = userRoleOptional.get();

        userRole.setDeletedDate(LocalDateTime.now());
        UserRole deletedUserRole = this.userRoleRepository.save(userRole);

        this.userRoleCacheService.removeFromUser(user.email(), role.name());

        return this.userRoleMapper.toDeletedUserRoleResponse(deletedUserRole);
    }

    @Override
    @Transactional
    public void clearUserRoles(int userId) {
        GetByIdUserResponse user = this.userService.getById(userId);

        List<UserRole> userRoles = this.userRoleRepository.findAllByUserId(user.id());
        userRoles.forEach(userRole -> userRole.setDeletedDate(LocalDateTime.now()));
        this.userRoleRepository.saveAll(userRoles);

        this.userRoleCacheService.clearUserRoles(user.email());
    }
}
