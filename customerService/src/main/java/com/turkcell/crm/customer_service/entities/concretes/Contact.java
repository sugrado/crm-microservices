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
@Table(name = "contacts")
public class Contact extends BaseEntity<Integer> {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "home_phone", nullable = false)
    private String homePhone;

    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @Column(name = "fax_number", nullable = false)
    private String faxNumber;

    @MapsId
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
