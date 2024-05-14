package com.turkcell.crm.account_service.entities.concretes;

import com.turkcell.crm.account_service.core.entities.BaseEntity;
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
@Table(name = "account_addresses")
public class AccountAddress extends BaseEntity<Integer> {
    @Column(name = "address_id")
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
