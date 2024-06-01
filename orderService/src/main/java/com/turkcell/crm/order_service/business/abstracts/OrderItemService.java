package com.turkcell.crm.order_service.business.abstracts;

import com.turkcell.crm.order_service.entities.concretes.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(List<OrderItem> orderItemList);

}
