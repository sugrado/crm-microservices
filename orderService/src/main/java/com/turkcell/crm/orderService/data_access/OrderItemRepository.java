package com.turkcell.crm.orderService.data_access;

import com.turkcell.crm.orderService.entities.concretes.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
