package com.turkcell.crm.account_service.entities.concretes;

import com.turkcell.crm.account_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account_addresses")
@SQLRestriction(value = "deleted_date is null")
public class AccountAddress extends BaseEntity<Integer> {
    @Column(name = "address_id", nullable = false)
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public AccountAddress(int id) {
        setId(id);
    }
}
