package com.turkcell.crm.search_service.business.abstracts;

import com.turkcell.crm.search_service.core.services.search.models.DynamicQuery;
import com.turkcell.crm.search_service.entities.concretes.IndividualOrder;

import java.util.List;

public interface IndividualOrderSearchService {
    void add(IndividualOrder order);

    void update(IndividualOrder order);

    void delete(int id);

    List<IndividualOrder> search(DynamicQuery dynamicQuery);
}
