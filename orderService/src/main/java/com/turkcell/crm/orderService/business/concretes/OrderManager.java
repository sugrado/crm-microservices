package com.turkcell.crm.orderService.business.concretes;

import com.turkcell.crm.orderService.api.clients.BasketClient;
import com.turkcell.crm.orderService.business.abstracts.OrderService;
import com.turkcell.crm.orderService.data_access.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final BasketClient basketClient;
}
