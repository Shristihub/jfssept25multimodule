package com.productorder.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import com.shared.models.PaymentFailedEvent;
import com.shared.models.PaymentSucceededEvent;

@Configuration
public class KafkaConfig {

	@Bean
	ConsumerFactory<String, PaymentSucceededEvent> successConsumerFactory() {
		// add the properties
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "order-service");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
				new JacksonJsonDeserializer<PaymentSucceededEvent>(PaymentSucceededEvent.class, false));
	}

	// create the container to store the event
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, PaymentSucceededEvent> successContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, PaymentSucceededEvent> factory = new ConcurrentKafkaListenerContainerFactory<String, PaymentSucceededEvent>();
		factory.setConsumerFactory(successConsumerFactory());
		return factory;

	}
	
	@Bean
	ConsumerFactory<String, PaymentFailedEvent> failedConsumerFactory(){
		// add some properties
		Map<String,Object> properties= new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG,"order-service");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(),new JacksonJsonDeserializer<>(PaymentFailedEvent.class,false));
		
	}
	//create the container to store the event
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent> failedContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent> factory = 
				new ConcurrentKafkaListenerContainerFactory<String, PaymentFailedEvent>();
		factory.setConsumerFactory(failedConsumerFactory());
		return factory;
		
	}

}
