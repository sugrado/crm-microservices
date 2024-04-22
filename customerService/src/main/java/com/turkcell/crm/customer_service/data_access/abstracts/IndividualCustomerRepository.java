package com.turkcell.crm.customer_service.data_access.abstracts;

import com.turkcell.crm.customer_service.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {
    Optional<IndividualCustomer> findByNationalityId(String nationalityId);
}
