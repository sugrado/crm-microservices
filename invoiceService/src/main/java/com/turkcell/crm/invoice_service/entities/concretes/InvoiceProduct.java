package com.turkcell.crm.invoice_service.entities.concretes;

import com.turkcell.crm.invoice_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice_products")
public class InvoiceProduct extends BaseEntity<UUID> {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}
