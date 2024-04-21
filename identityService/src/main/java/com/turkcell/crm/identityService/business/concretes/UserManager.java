package com.turkcell.crm.identityService.business.concretes;

import com.turkcell.crm.identityService.business.abstracts.UserService;
import com.turkcell.crm.identityService.business.constants.Messages;
import com.turkcell.crm.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.identityService.data_access.abstracts.UserRepository;
import com.turkcell.crm.identityService.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(Messages.AuthMessages.LOGIN_FAILED));
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(Messages.AuthMessages.LOGIN_FAILED));
    }


}
