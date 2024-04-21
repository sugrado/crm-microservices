package com.turkcell.crm.identityService.business.abstracts;

import com.turkcell.crm.identityService.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    User add(User user);
}
