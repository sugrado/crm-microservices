package com.turkcell.crm.account_service.data_access.abstracts;

import com.turkcell.crm.account_service.entities.concretes.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByCustomerId(int customerId);
}
