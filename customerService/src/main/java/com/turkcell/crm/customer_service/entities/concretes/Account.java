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
@Table(name = "accounts")
public class Account extends BaseEntity<Integer> {
    @Column(name = "status")
    private String Status;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<AccountAddress> accountAddresses;
}
