package com.turkcell.crm.invoice_service.entities.concretes;

import com.turkcell.crm.invoice_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "individual_invoices")
@SQLRestriction(value = "deleted_date is null")
public class IndividualInvoice extends BaseEntity<UUID> {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nationalityId", nullable = false)
    private String nationalityId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
