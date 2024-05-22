package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.Product;

import java.util.List;

public interface ProductSearchService {
    void add(Product product);

    void update(Product product);

    void delete(int productId);
    List<Product> searchProducts(DynamicQuery dynamicQuery);
}
