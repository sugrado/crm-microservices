package com.turkcell.crm.identity_service.data_access.abstracts;

import com.turkcell.crm.identity_service.entities.concretes.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(int userId);

    boolean existsByUserIdAndRoleId(int userId, int roleId);

    Optional<UserRole> findByUserIdAndRoleId(int userId, int roleId);
}
