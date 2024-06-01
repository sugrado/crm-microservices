package com.turkcell.crm.search_service.business.concretes;

import com.turkcell.crm.search_service.business.abstracts.IndividualOrderSearchService;
import com.turkcell.crm.search_service.core.services.search.SearchService;
import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.data_access.abstracts.IndividualOrderRepository;
import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualOrderSearchManager implements IndividualOrderSearchService {
    private final IndividualOrderRepository orderRepository;
    private final SearchService searchService;

    @Override
    public void add(IndividualOrder order) {
        this.orderRepository.save(order);
    }

    @Override
    public void update(IndividualOrder order) {
        this.orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public List<IndividualOrder> search(DynamicQuery dynamicQuery) {
        return searchService.dynamicSearch(dynamicQuery, IndividualOrder.class);
    }
}
