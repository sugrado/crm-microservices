package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
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
@Table(name = "customers")
public class Customer extends BaseEntity<Integer> {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer")
    private IndividualCustomer individualCustomer;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;
}