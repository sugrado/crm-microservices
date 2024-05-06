package com.turkcell.crm.customer_service.data_access.abstracts;

import com.turkcell.crm.customer_service.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByCustomerIdAndIdIsIn(int customerId, List<Integer> ids);

    Optional<Address> findByIsDefaultAddressTrueAndCustomerId(int customerId);
}
