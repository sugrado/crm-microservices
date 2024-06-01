package com.turkcell.crm.order_service.business.concretes;

import com.turkcell.crm.common.shared.dtos.accounts.GetByIdAccountAddressResponse;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import com.turkcell.crm.common.shared.dtos.catalogs.GetAllForCompleteOrderResponse;
import com.turkcell.crm.common.shared.dtos.customers.GetIndividualCustomerInvoiceInfoDto;
import com.turkcell.crm.common.shared.kafka.events.orders.IndividualOrderCreatedEvent;
import com.turkcell.crm.order_service.api.clients.AccountClient;
import com.turkcell.crm.order_service.api.clients.BasketClient;
import com.turkcell.crm.order_service.api.clients.CatalogClient;
import com.turkcell.crm.order_service.api.clients.CustomerClient;
import com.turkcell.crm.order_service.business.abstracts.BaseOrderManager;
import com.turkcell.crm.order_service.business.abstracts.IndividualOrderService;
import com.turkcell.crm.order_service.business.abstracts.OrderItemService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.mappers.OrderMapper;
import com.turkcell.crm.order_service.data_access.OrderRepository;
import com.turkcell.crm.order_service.entities.concretes.Order;
import com.turkcell.crm.order_service.entities.concretes.OrderItem;
import com.turkcell.crm.order_service.kafka.OrderProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualOrderManager extends BaseOrderManager implements IndividualOrderService {
    private final BasketClient basketClient;
    private final CatalogClient catalogClient;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final AccountClient accountClient;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;
    private final CustomerClient customerClient;

    public IndividualOrderManager(OrderRepository orderRepository,
                                  AccountClient accountClient,
                                  OrderMapper orderMapper,
                                  BasketClient basketClient,
                                  CatalogClient catalogClient,
                                  OrderItemService orderItemService, OrderProducer orderProducer, CustomerClient customerClient) {
        super(orderRepository, accountClient, orderMapper);
        this.basketClient = basketClient;
        this.catalogClient = catalogClient;
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.accountClient = accountClient;
        this.orderMapper = orderMapper;
        this.orderProducer = orderProducer;
        this.customerClient = customerClient;
    }

    @Override
    @Transactional
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

        GetIndividualCustomerInvoiceInfoDto invoiceInfo = this.customerClient.getInvoiceInfoByAddress(accountAddress.addressId());

        IndividualOrderCreatedEvent individualOrderCreatedEvent = this.orderMapper.toIndividualOrderCreatedEvent(invoiceInfo);
        individualOrderCreatedEvent.setAccountId(order.getAccountId());
        individualOrderCreatedEvent.setOrderId(order.getId());
        individualOrderCreatedEvent.setTotalPrice(order.getTotalPrice());
        individualOrderCreatedEvent.setProducts(this.orderMapper.toOrderCreatedEventProducts(products));
        this.orderProducer.send(individualOrderCreatedEvent);
    }
}
