package com.turkcell.crm.order_service.data_access;

import com.turkcell.crm.order_service.entities.concretes.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
