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
@Table(name = "property")
public class Property extends BaseEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<ProductProperty> productProperties;
}
