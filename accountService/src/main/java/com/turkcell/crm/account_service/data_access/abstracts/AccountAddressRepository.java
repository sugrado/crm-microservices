package com.turkcell.crm.account_service.data_access.abstracts;

import com.turkcell.crm.account_service.entities.concretes.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountAddressRepository extends JpaRepository<AccountAddress, Integer> {
    // TODO: feign ile kontrol edilecek
//  @Query("select COUNT(a) > 0 from Account a where a.id = :accountId and a.customerId = (select ad.customer.id from Address ad where ad.id = :addressId)")
//    boolean accountCustomerAndAddressCustomerMatch(int accountId, int addressId);

    boolean existsByAccountIdAndAddressId(int accountId, int addressId);

    List<AccountAddress> findAllAccountAddressesByAccountId(int accountId);
}
