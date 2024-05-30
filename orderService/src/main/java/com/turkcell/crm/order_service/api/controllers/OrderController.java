package com.turkcell.crm.order_service.api.controllers;


import com.turkcell.crm.order_service.business.abstracts.OrderService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-service/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public void complete(@Valid @RequestBody CompleteOrderRequest request){
        this.orderService.complete(request);
    }

    @GetMapping
    public List<GetAllOrdersResponse> getAll(){
        return this.orderService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdOrderResponse getById(@PathVariable int id){
        return this.orderService.getById(id);
    }

    @GetMapping("get-all-orders-by-account-id/{accountId}")
    public List<GetAllOrdersByAccountIdResponse> getAllOrdersByAccountId(@PathVariable int accountId){
        return this.orderService.getAllOrdersByAccountId(accountId);
    }
}
