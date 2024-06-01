package com.turkcell.crm.order_service.business.mappers;

import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import com.turkcell.crm.common.shared.dtos.customers.GetIndividualCustomerInvoiceInfoDto;
import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.common.shared.kafka.events.orders.OrderCreatedEventProduct;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import com.turkcell.crm.order_service.entities.concretes.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface OrderMapper {

    List<GetAllOrdersResponse> toGetAllOrdersResponseList(List<Order> orders);

    GetAllOrdersByAccountIdResponse toGetAllOrdersByAccountIdResponse(Order order);

    List<GetAllOrdersByAccountIdResponse> toGetAllOrdersByAccountIdResponseList(List<Order> orders);

    GetByIdOrderResponse toGetByIdOrderResponse(Order order);

    Order toOrder(CompleteOrderRequest completeOrderRequest);

    IndividualOrderCreatedEvent toIndividualOrderCreatedEvent(GetIndividualCustomerInvoiceInfoDto getIndividualCustomerInvoiceInfoDto);

    List<OrderCreatedEventProduct> toOrderCreatedEventProducts(List<GetAllForCompleteOrderResponse> getAllForCompleteOrderResponse);
}
