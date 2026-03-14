package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Product;

@Service
public class OrderServiceImpl implements IOrderService{

	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Product> template;

	@Value("${order.topicname}")
	private String topicName;

  	@Override
	public void sendStatus(String message) {
     System.out.println("Sending message");
	  // send message to KafkaServer
	  kafkaTemplate.send(topicName, message);
	  logger.info("message send to Kafka topic: "+topicName);
	
	
	}

	@Override
	public String placeOrder(Product product) {
		 // send message to KafkaServer
		  template.send("prod-topic", product);
		  logger.info("event send to kafka: "+ product);
		return "Order placed successfully";
	}

	
}
