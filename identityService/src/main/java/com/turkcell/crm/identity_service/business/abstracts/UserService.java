package com.turkcell.crm.identity_service.business.abstracts;

import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetByIdUserResponse;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    User add(User user);

    GetByIdUserResponse getById(int userId);
}
