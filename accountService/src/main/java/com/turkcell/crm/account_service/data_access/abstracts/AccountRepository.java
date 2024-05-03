package com.turkcell.crm.account_service.data_access.abstracts;

import com.turkcell.crm.account_service.entities.concretes.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
