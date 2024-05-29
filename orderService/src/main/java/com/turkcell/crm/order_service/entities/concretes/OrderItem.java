package com.turkcell.crm.order_service.entities.concretes;

import com.turkcell.crm.order_service.core.entities.BaseEntity;
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
@Table(name = "order_items")
public class OrderItem extends BaseEntity<Integer> {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
