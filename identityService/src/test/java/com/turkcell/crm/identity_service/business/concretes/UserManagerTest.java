package com.turkcell.crm.identity_service.business.concretes;

import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.identity_service.business.rules.UserBusinessRules;
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
        userRepository = mock(UserRepository.class);
        UserBusinessRules userBusinessRules = new UserBusinessRules();
        userManager = new UserManager(userRepository, userBusinessRules);
    }

    @Test
    void findByUsername_withNotExistingUser_ShouldThrowException() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> {
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
        assertThrows(BusinessException.class, () -> {
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