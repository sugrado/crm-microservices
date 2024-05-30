package com.turkcell.crm.order_service.business.concretes;

import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import com.turkcell.crm.order_service.api.clients.AccountClient;
import com.turkcell.crm.order_service.api.clients.BasketClient;
import com.turkcell.crm.order_service.api.clients.CatalogClient;
import com.turkcell.crm.order_service.business.abstracts.OrderItemService;
import com.turkcell.crm.order_service.business.abstracts.OrderService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import com.turkcell.crm.order_service.business.mappers.OrderMapper;
import com.turkcell.crm.order_service.data_access.OrderRepository;
import com.turkcell.crm.order_service.entities.concretes.Order;
import com.turkcell.crm.order_service.entities.concretes.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketClient basketClient;
    private final CatalogClient catalogClient;
    private final AccountClient accountClient;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;

    @Override
    public void complete(CompleteOrderRequest request) {
        this.accountClient.getByIdAccount(request.accountId());

        List<GetProductsFromBasketDto> productsFromBasket = this.basketClient.getProductsFromBasket(String.valueOf(request.accountId()));
        List<Integer> ids = productsFromBasket.stream().map(p -> p.productId()).toList();
        List<GetAllForCompleteOrderResponse> products = this.catalogClient.getAllForCompleteOrder(ids);
        GetByIdAccountAddressResponse accountAddress = this.accountClient.getAccountAddress(request.accountId(), request.addressId());

        Order order = this.orderMapper.toOrder(request);
        order.setAccountAddressId(accountAddress.id());
        order.setTotalPrice(products.stream().mapToDouble(GetAllForCompleteOrderResponse::price).sum());
        this.orderRepository.save(order);

        List<OrderItem> orderItemsToCreate = products.stream().map(p -> new OrderItem(p.id(), p.price(), order)).toList();
        this.orderItemService.add(orderItemsToCreate);
    }

    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orderList = this.orderRepository.findAll();

        return this.orderMapper.toGetAllOrdersResponseList(orderList);
    }

    @Override
    public GetByIdOrderResponse getById(int id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);

        //this.orderBusinessRules.orderShouldBeExist(optionalOrder);
        //this.orderBusinessRules.orderShouldNotBeDeleted(optionalOrder);

        return this.orderMapper.toGetByIdOrderResponse(optionalOrder.get());
    }
    //TODO: methodun mapperında bir gariplik var bir hata veriyor bir hata vermiyor
    @Override
    public List<GetAllOrdersByAccountIdResponse> getAllOrdersByAccountId(int id) {
        this.accountClient.getByIdAccount(id);

        List<Order> orderList = this.orderRepository.findAllByAccountId(id);

        return this.orderMapper.toGetAllOrdersByAccountIdResponseList(orderList);
    }


}
