package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.common.shared.exceptions.types.AuthenticationException;
import com.turkcell.crm.identity_service.business.mappers.UserMapper;
import com.turkcell.crm.identity_service.business.mappers.UserMapperImpl;
import com.turkcell.crm.identity_service.business.rules.UserBusinessRules;
import com.turkcell.crm.identity_service.core.business.abstracts.MessageService;
import com.turkcell.crm.identity_service.data_access.abstracts.UserRepository;
import com.turkcell.crm.identity_service.entities.concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserManagerTest {
    private UserRepository userRepository;
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        MessageService messageService = mock(MessageService.class);
        userRepository = mock(UserRepository.class);
        UserBusinessRules userBusinessRules = new UserBusinessRules(messageService);
        UserMapper userMapper = new UserMapperImpl();
        userManager = new UserManager(userRepository, userBusinessRules, userMapper);
    }

    @Test
    void findByUsername_withNotExistingUser_ShouldThrowException() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(AuthenticationException.class, () -> {
            userManager.findByUsername("gorkem");
        });
    }

    @Test
    void findByUsername_withExistingUser_ShouldBeSuccess() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new User()));
        User res = userManager.findByUsername("gorkem");
        assertNotNull(res);
    }

    @Test
    void add_shouldBeSuccess() {
        User user = new User();
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User res = userManager.add(user);
        assertNotNull(res);
    }

    @Test
    void loadUserByUsername_withNotExistingUser_ShouldThrowException() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(AuthenticationException.class, () -> {
            userManager.loadUserByUsername("gorkem");
        });
    }

    @Test
    void loadUserByUsername_withExistingUser_ShouldBeSuccess() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new User()));
        UserDetails res = userManager.loadUserByUsername("gorkem");
        assertNotNull(res);
    }
}