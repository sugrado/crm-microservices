package com.turkcell.crm.order_service.business.abstracts;

import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;

import java.util.List;

public interface IndividualOrderService {
    void complete(CompleteOrderRequest request);

    List<GetAllOrdersResponse> getAll();

    GetByIdOrderResponse getById(int id);

    List<GetAllOrdersByAccountIdResponse> getAllOrdersByAccountId(int id);
}
