package com.turkcell.crm.catalog_service.data_access.abstracts;

import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Integer> {
    boolean existsByProductIdAndPropertyId(int productId, int propertyId);
}
