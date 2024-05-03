package com.turkcell.crm.account_service.data_access.abstracts;

import com.turkcell.crm.account_service.entities.concretes.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
