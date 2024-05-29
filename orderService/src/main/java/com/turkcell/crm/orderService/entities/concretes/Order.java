package com.turkcell.crm.orderService.entities.concretes;

import com.turkcell.crm.orderService.core.entities.BaseEntity;
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
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {
    @Column(name = "account_address_id")
    private int accountAddressId;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}

