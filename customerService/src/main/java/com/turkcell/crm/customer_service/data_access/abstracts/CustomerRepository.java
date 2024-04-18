package com.turkcell.crm.customer_service.data_access.abstracts;

import com.turkcell.crm.customer_service.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByNationalityId(String nationalityId);
}
