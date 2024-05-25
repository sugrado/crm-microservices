package com.turkcell.crm.search_service.business.concretes;

import com.turkcell.crm.search_service.core.services.search.SearchService;
import com.turkcell.crm.search_service.core.services.search.enums.FilterOperator;
import com.turkcell.crm.search_service.core.services.search.enums.SortDirection;
import com.turkcell.crm.search_service.core.services.search.models.DynamicFilter;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.core.services.search.models.DynamicSort;
import com.turkcell.crm.search_service.data_access.abstracts.ProductRepository;
import com.turkcell.crm.search_service.entities.concretes.Product;
import com.turkcell.crm.search_service.entities.concretes.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductSearchManagerTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private ProductSearchManager productSearchManager;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    public void testAddProduct() {
        this.productSearchManager.add(product);
        assertTrue(true);
        verify(this.productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        this.productSearchManager.update(product);
        assertTrue(true);
        verify(this.productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        int productId = 1;
        this.productSearchManager.delete(productId);
        assertTrue(true);
        verify(this.productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testSearchProducts() {
        DynamicQuery dynamicQuery = new DynamicQuery(
                List.of(new DynamicFilter("productName", FilterOperator.EQUALS, "Mouse")),
                List.of(new DynamicSort("categoryName", SortDirection.ASC))
        );
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());

        when(this.searchService.dynamicSearch(any(DynamicQuery.class), eq(Product.class))).thenReturn(expectedProducts);

        List<Product> actualProducts = this.productSearchManager.searchProducts(dynamicQuery);

        assertEquals(expectedProducts, actualProducts);
        verify(this.searchService, times(1)).dynamicSearch(dynamicQuery, Product.class);
    }
}
