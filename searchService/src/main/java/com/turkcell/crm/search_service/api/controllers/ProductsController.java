package com.turkcell.crm.search_service.api.controllers;

import com.turkcell.crm.search_service.business.abstracts.ProductSearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("search-service/api/v1/products")
public class ProductsController {
    private final ProductSearchService productSearchService;

    @PostMapping("/dynamic-search")
    public List<Product> searchProducts(@RequestBody DynamicQuery dynamicQuery) {
        return this.productSearchService.searchProducts(dynamicQuery);
    }
}
