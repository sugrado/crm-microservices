package com.turkcell.crm.order_service.business.abstracts;


import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;

public interface OrderService {
    void complete(CompleteOrderRequest request);
}
