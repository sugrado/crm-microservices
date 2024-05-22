package com.turkcell.crm.search_service.business.mappers;

import com.turkcell.crm.common.kafka.events.ProductCreatedEvent;
import com.turkcell.crm.common.kafka.events.ProductUpdatedEvent;
import com.turkcell.crm.search_service.core.utilities.mapping.MapstructService;
import com.turkcell.crm.search_service.entities.concretes.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructService.class)
public interface ProductMapper {
    @Mapping(source = "title",target = "productName")
    Product toProduct(ProductCreatedEvent productCreatedEvent);

    @Mapping(source = "title",target = "productName")
    Product toProduct(ProductUpdatedEvent productUpdatedEvent);
}
