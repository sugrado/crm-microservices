package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity<Integer> {

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_flat_number", nullable = false)
    private String houseFlatNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "default_address", nullable = false)
    private boolean defaultAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;
}
