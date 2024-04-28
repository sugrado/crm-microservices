package com.turkcell.crm.searchService;

import com.turkcell.crm.common.events.CustomerCreatedEvent;
import com.turkcell.crm.searchService.kafka.handlers.KafkaErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;

@SpringBootApplication
public class SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

	@Bean
	CommonErrorHandler commonErrorHandler() {
		return new KafkaErrorHandler();
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, CustomerCreatedEvent> kafkaListenerContainerFactory(
			ConsumerFactory<String, CustomerCreatedEvent> consumerFactory,
			CommonErrorHandler commonErrorHandler
	) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, CustomerCreatedEvent>();
		factory.setConsumerFactory(consumerFactory);
		factory.setCommonErrorHandler(commonErrorHandler);
		return factory;
	}
}
