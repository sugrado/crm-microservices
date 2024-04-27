package com.turkcell.crm.customer_service.data_access.abstracts;

import com.turkcell.crm.customer_service.entities.concretes.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountAddressRepository extends JpaRepository<AccountAddress, Integer> {
    @Query("select COUNT(a) > 0 from Account a where a.id = :accountId and a.customer.id = (select ad.customer.id from Address ad where ad.id = :addressId)")
    boolean accountCustomerAndAddressCustomerMatch(int accountId, int addressId);

    boolean existsByAccountIdAndAddressId(int accountId, int addressId);
}
