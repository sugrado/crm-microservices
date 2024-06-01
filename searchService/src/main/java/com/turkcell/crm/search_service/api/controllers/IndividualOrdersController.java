package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.IndividualOrderSearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("search-service/api/v1/orders")
public class IndividualOrdersController {
    private final IndividualOrderSearchService orderSearchService;

    @PostMapping("/dynamic-search")
    @ResponseStatus(HttpStatus.OK)
    public List<IndividualOrder> searchAccounts(@RequestBody DynamicQuery dynamicQuery) {
        return orderSearchService.search(dynamicQuery);
    }
}
