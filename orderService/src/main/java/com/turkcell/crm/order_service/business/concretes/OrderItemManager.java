package com.turkcell.crm.order_service.business.concretes;

import com.turkcell.crm.order_service.business.abstracts.OrderItemService;
import com.turkcell.crm.order_service.data_access.OrderItemRepository;
import com.turkcell.crm.order_service.entities.concretes.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public void add(List<OrderItem> orderItemList) {
        this.orderItemRepository.saveAll(orderItemList);
    }
}
