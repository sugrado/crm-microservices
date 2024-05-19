package com.turkcell.crm.catalog_service.entities.concretes;

import com.turkcell.crm.catalog_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Property> properties;

    public Category(int id) {
        setId(id);
    }

}
