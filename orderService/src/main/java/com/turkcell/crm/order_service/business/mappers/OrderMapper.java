package com.turkcell.crm.order_service.business.mappers;

import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import com.turkcell.crm.order_service.entities.concretes.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface OrderMapper {
    GetAllOrdersResponse toGetAllOrdersResponse(Order order);

    List<GetAllOrdersResponse> toGetAllOrdersResponseList(List<Order> orders);

    @Mapping(target = "accountAddressId",source = "accountAddressId")
    @Mapping(target = "accountId",source = "accountId")
    @Mapping(target = "totalPrice",source = "totalPrice")
    GetAllOrdersByAccountIdResponse toGetAllOrdersByAccountIdResponse(Order order);

    List<GetAllOrdersByAccountIdResponse> toGetAllOrdersByAccountIdResponseList(List<Order> orders);

    GetByIdOrderResponse toGetByIdOrderResponse(Order order);

    Order toOrder(CompleteOrderRequest completeOrderRequest);
}
