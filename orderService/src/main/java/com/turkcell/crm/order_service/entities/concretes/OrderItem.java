package com.turkcell.crm.order_service.entities.concretes;

import com.turkcell.crm.order_service.core.entities.BaseEntity;
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
@Table(name = "order_items")
@SQLRestriction(value = "deleted_date is null")
public class OrderItem extends BaseEntity<Integer> {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
