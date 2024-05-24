package com.turkcell.crm.account_service.data_access.abstracts;

import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAddressRepository extends JpaRepository<AccountAddress, Integer> {
    boolean existsByAccountIdAndAddressId(int accountId, int addressId);

    List<AccountAddress> findAllAccountAddressesByAccountId(int accountId);

    Optional<AccountAddress> findByAccountIdAndAddressId(int accountId, int addressId);
}
