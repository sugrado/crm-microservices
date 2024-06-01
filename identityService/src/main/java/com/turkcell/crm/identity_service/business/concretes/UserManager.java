package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.identity_service.business.abstracts.UserService;
import com.turkcell.crm.identity_service.business.dtos.responses.user_roles.GetByIdUserResponse;
import com.turkcell.crm.identity_service.business.mappers.UserMapper;
import com.turkcell.crm.identity_service.business.rules.UserBusinessRules;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import com.turkcell.crm.identity_service.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = this.userRepository.findByEmail(username);
        this.userBusinessRules.userShouldBeExist(userOptional);
        return userOptional.get();
    }

    @Override
    public User add(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public GetByIdUserResponse getById(int userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        this.userBusinessRules.userShouldBeExists(userOptional);
        return this.userMapper.toGetByIdUserResponse(userOptional.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username);
    }
}
