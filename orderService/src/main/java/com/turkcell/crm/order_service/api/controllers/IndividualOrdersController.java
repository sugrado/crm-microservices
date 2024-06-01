package com.turkcell.crm.order_service.api.controllers;


import com.turkcell.crm.order_service.business.abstracts.IndividualOrderService;
import com.turkcell.crm.order_service.business.dtos.requests.CompleteOrderRequest;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersByAccountIdResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetAllOrdersResponse;
import com.turkcell.crm.order_service.business.dtos.responses.GetByIdOrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-service/api/v1/individual-orders")
@RequiredArgsConstructor
public class IndividualOrdersController {
    private final IndividualOrderService individualOrderService;

    @PostMapping
    public void complete(@Valid @RequestBody CompleteOrderRequest request) {
        this.individualOrderService.complete(request);
    }

    @GetMapping
    public List<GetAllOrdersResponse> getAll() {
        return this.individualOrderService.getAll();
    }

    @GetMapping("{id}")
    public GetByIdOrderResponse getById(@PathVariable int id) {
        return this.individualOrderService.getById(id);
    }

    @GetMapping("get-all-orders-by-account-id/{accountId}")
    public List<GetAllOrdersByAccountIdResponse> getAllOrdersByAccountId(@PathVariable int accountId) {
        return this.individualOrderService.getAllOrdersByAccountId(accountId);
    }
}
