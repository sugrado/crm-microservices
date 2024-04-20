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
@Table(name = "invoice_addresses")
public class InvoiceAddress extends BaseEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "flat_number", nullable = false)
    private String flatNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
