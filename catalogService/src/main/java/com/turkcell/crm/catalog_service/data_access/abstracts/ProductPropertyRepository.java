package com.turkcell.crm.catalog_service.data_access.abstracts;

import com.turkcell.crm.catalog_service.entities.concretes.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Integer> {
    boolean existsByProductIdAndPropertyId(int productId, int propertyId);
    List<ProductProperty> findAllByProductId(int productId);
    Optional<ProductProperty> findByProductIdAndId(int productId, int id);
    List<ProductProperty> findByPropertyIdAndProductId(int propertyId, int productId);

}
