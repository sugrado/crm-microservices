package com.turkcell.crm.order_service.business.concretes;

import com.turkcell.crm.order_service.api.clients.BasketClient;
import com.turkcell.crm.order_service.business.abstracts.OrderService;
import com.turkcell.crm.order_service.data_access.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final BasketClient basketClient;
}
