package com.turkcell.crm.catalog_service.data_access.abstracts;

import com.turkcell.crm.catalog_service.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
