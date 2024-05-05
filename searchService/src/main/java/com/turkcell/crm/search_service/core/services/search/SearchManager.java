package com.turkcell.crm.search_service.core.services.search;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchManager implements SearchService {
    private final MongoTemplate mongoTemplate;

    @Override
    public <T> List<T> dynamicSearch(Map<String, String> searchParams, Class<T> type) {
        Query query = new Query();
        searchParams.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));

        return mongoTemplate.find(query, type);
    }
}
