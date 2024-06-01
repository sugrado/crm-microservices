package com.turkcell.crm.identity_service.api.controllers;

import com.turkcell.crm.identity_service.business.abstracts.UserRoleService;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.CreateUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.requests.user_roles.DeleteUserRoleRequest;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.CreatedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.DeletedUserRoleResponse;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetUserRolesByUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/identity-service/api/v1/users/{userId}/roles")
@RequiredArgsConstructor
public class UserRolesController {
    private final UserRoleService userRoleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserRolesByUserResponse> getUserRoles(@PathVariable int userId) {
        return this.userRoleService.getAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserRoleResponse addUserRole(@PathVariable int userId, @RequestBody CreateUserRoleRequest createUserRoleRequest) {
        return this.userRoleService.addRoleToUser(userId, createUserRoleRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedUserRoleResponse removeUserRole(@PathVariable int userId, @RequestBody DeleteUserRoleRequest deleteUserRoleRequest) {
        return this.userRoleService.deleteRoleFromUser(userId, deleteUserRoleRequest);
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUserRoles(@PathVariable int userId) {
        this.userRoleService.clearUserRoles(userId);
    }
}
