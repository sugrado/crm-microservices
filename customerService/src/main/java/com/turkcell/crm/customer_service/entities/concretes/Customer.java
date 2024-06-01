package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@SQLRestriction(value = "deleted_date is null")
public class Customer extends BaseEntity<Integer> {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_phone", nullable = false, length = 11)
    private String mobilePhone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private IndividualCustomer individualCustomer;
}