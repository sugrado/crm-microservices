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
    // TODO: videoya bakalım
    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private AccountType type;

    @OneToMany(mappedBy = "account")
    private List<AccountAddress> addresses;

    public Account(int id) {
        setId(id);
    }
}
