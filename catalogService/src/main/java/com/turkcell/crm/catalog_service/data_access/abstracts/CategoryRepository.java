package com.turkcell.crm.catalog_service.data_access.abstracts;

import com.turkcell.crm.catalog_service.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndIdIsNot(String name, int id);
}
