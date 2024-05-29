package com.turkcell.crm.order_service.data_access;

import com.turkcell.crm.order_service.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
