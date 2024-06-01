package com.turkcell.crm.catalog_service.entities.concretes;

import com.turkcell.crm.catalog_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_properties")
@SQLRestriction(value = "deleted_date is null")
public class ProductProperty extends BaseEntity<Integer> {

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
