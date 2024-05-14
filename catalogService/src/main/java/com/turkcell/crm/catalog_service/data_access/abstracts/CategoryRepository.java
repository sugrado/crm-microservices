package com.turkcell.crm.catalog_service.data_access.abstracts;

import com.turkcell.crm.catalog_service.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
