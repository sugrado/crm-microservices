package com.turkcell.crm.order_service.entities.concretes;

import com.turkcell.crm.order_service.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "orders")
@SQLRestriction(value = "deleted_date is null")
public class Order extends BaseEntity<Integer> {
    @Column(name = "account_address_id")
    private int accountAddressId;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
