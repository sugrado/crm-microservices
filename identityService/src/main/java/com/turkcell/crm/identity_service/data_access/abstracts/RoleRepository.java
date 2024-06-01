package com.turkcell.crm.identity_service.data_access.abstracts;

import com.turkcell.crm.identity_service.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
