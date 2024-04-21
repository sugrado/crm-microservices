package com.turkcell.crm.identityService.data_access.abstracts;

import com.turkcell.crm.identityService.entities.concretes.RefreshToken;
import com.turkcell.crm.identityService.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUserAndRevokedDateIsNullAndExpirationDateBefore(User user, LocalDateTime expirationDate);
}
