package com.turkcell.crm.search_service.business.concretes;

import com.turkcell.crm.search_service.business.abstracts.ProductSearchService;
import com.turkcell.crm.search_service.core.services.search.SearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.search_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchManager implements ProductSearchService {
    private final ProductRepository productRepository;
    private final SearchService searchService;

    @Override
    public void add(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(int productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public List<Product> search(DynamicQuery dynamicQuery) {
        return searchService.dynamicSearch(dynamicQuery, Product.class);
    }

}
