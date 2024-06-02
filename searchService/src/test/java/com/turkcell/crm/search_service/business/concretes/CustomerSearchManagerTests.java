package com.turkcell.crm.search_service.business.concretes;

import com.turkcell.crm.search_service.business.dtos.responses.individual_customers.SearchIndividualCustomersResponse;
import com.turkcell.crm.search_service.core.services.search.SearchService;
import com.turkcell.crm.search_service.core.services.search.enums.FilterOperator;
import com.turkcell.crm.search_service.core.services.search.enums.SortDirection;
import com.turkcell.crm.search_service.core.services.search.models.DynamicFilter;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.core.services.search.models.DynamicSort;
import com.turkcell.crm.search_service.core.services.search.models.Pagination;
import com.turkcell.crm.search_service.data_access.abstracts.IndividualCustomerRepository;
import com.turkcell.crm.search_service.entities.concretes.IndividualCustomer;
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
public class CustomerSearchManagerTests {

    @Mock
    private IndividualCustomerRepository customerRepository;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private IndividualCustomerSearchManager customerSearchManager;

    private IndividualCustomer customer;

    @BeforeEach
    void setUp() {
        customer = new IndividualCustomer();
    }

    @Test
    public void testAddCustomer() {
        this.customerSearchManager.add(customer);
        assertTrue(true);
        verify(this.customerRepository, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomer() {
        this.customerSearchManager.update(customer);
        assertTrue(true);
        verify(this.customerRepository, times(1)).save(customer);
    }

    @Test
    public void testDeleteCustomer() {
        int customerId = 1;
        this.customerSearchManager.delete(customerId);
        assertTrue(true);
        verify(this.customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testSearchCustomers() {
        DynamicQuery dynamicQuery = new DynamicQuery(
                List.of(new DynamicFilter("firstName", FilterOperator.EQUALS, "Engin")),
                List.of(new DynamicSort("lastName", SortDirection.ASC))
                ,new Pagination(1,5)
        );
        List<IndividualCustomer> expectedCustomers = Arrays.asList(new IndividualCustomer(), new IndividualCustomer());

        when(this.searchService.dynamicSearch(any(DynamicQuery.class), eq(IndividualCustomer.class))).thenReturn(expectedCustomers);

        List<SearchIndividualCustomersResponse> actualCustomers = this.customerSearchManager.search(dynamicQuery);

        assertEquals(expectedCustomers, actualCustomers);
        verify(this.searchService, times(1)).dynamicSearch(dynamicQuery, IndividualCustomer.class);
    }
}
