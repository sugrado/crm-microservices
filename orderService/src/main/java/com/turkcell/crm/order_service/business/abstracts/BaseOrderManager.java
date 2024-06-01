package com.turkcell.crm.order_service.business.abstracts;

import com.turkcell.crm.order_service.api.clients.AccountClient;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import com.turkcell.crm.order_service.business.mappers.OrderMapper;
import com.turkcell.crm.order_service.business.rules.OrderBusinessRules;
import com.turkcell.crm.order_service.data_access.OrderRepository;
import com.turkcell.crm.order_service.entities.concretes.Order;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseOrderManager {
    private final OrderRepository orderRepository;
    private final AccountClient accountClient;
    private final OrderMapper orderMapper;
    private final OrderBusinessRules orderBusinessRules;

    public abstract void complete(CompleteOrderRequest request);

    public List<GetAllOrdersResponse> getAll() {
        List<Order> orderList = this.orderRepository.findAll();
        return this.orderMapper.toGetAllOrdersResponseList(orderList);
    }

    public GetByIdOrderResponse getById(int id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        this.orderBusinessRules.orderShouldBeExist(optionalOrder);

        return this.orderMapper.toGetByIdOrderResponse(optionalOrder.get());
    }

    public List<GetAllOrdersByAccountIdResponse> getAllOrdersByAccountId(int id) {
        this.accountClient.getByIdAccount(id);
        List<Order> orderList = this.orderRepository.findAllByAccountId(id);

        return this.orderMapper.toGetAllOrdersByAccountIdResponseList(orderList);
    }
}
