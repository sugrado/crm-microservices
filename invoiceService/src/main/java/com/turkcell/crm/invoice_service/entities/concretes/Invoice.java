package com.turkcell.crm.invoice_service.entities.concretes;

import com.turkcell.crm.invoice_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoices")
@SQLRestriction(value = "deleted_date is null")
@Builder
public class Invoice extends BaseEntity<UUID> {
    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobilePhone", nullable = false)
    private String mobilePhone;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
    private IndividualInvoice individual;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceProduct> products;
}
