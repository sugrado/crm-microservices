package com.turkcell.crm.search_service.kafka.consumers;

import com.turkcell.crm.common.kafka.events.ProductCreatedEvent;
import com.turkcell.crm.common.kafka.events.ProductDeletedEvent;
import com.turkcell.crm.common.kafka.events.ProductUpdatedEvent;
import com.turkcell.crm.search_service.business.abstracts.ProductSearchService;
import com.turkcell.crm.search_service.business.mappers.ProductMapper;
import com.turkcell.crm.search_service.entities.concretes.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private final ProductMapper productMapper;
    private final ProductSearchService productSearchService;

    @KafkaListener(topics = "product-created", groupId = "product.group")
    public void consume(ProductCreatedEvent productCreatedEvent) {
        Product product = this.productMapper.toProduct(productCreatedEvent);
        this.productSearchService.add(product);
    }

    @KafkaListener(topics = "product-updated", groupId = "product.group")
    public void consume(ProductUpdatedEvent productUpdatedEvent){
        Product product = this.productMapper.toProduct(productUpdatedEvent);
        this.productSearchService.update(product);
    }
    @KafkaListener(topics = "product-deleted", groupId = "product.group")
    public void consume(ProductDeletedEvent productDeletedEvent){
        this.productSearchService.delete(productDeletedEvent.getId());
    }
}
