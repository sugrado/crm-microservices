package com.turkcell.crm.customer_service.data_access.abstracts;

import com.turkcell.crm.customer_service.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
