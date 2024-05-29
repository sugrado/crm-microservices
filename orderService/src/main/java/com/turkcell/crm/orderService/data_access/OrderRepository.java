package com.turkcell.crm.orderService.data_access;

import com.turkcell.crm.orderService.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
