package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.model.Product;

@Service
public class InventoryServiceImpl implements IInventoryService{
	
	@Value("${order.topicname}")
	private String topicName;
	private String messageStatus;
	Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);


	@Override
	public String getStatus() {
//		send this to controller
		return messageStatus;
	}
	
	  @KafkaListener(topics = {"order-topic"}, groupId = "order_group_id")
	  void consumeMessage(String messageReceived) {
		   //receive/consume the message
		   System.out.println(messageReceived);
		   logger.info("status: "+messageReceived);
		   // aasign the consumed message to the statusmessage
		   messageStatus = messageReceived;
	   
	  }
	  
	  @KafkaListener(topics = {"prod-topic"}, groupId = "order_group_id")
	  void consumeProductDetails(Product product) {
		   //receive/consume the message
		   System.out.println(product);
		   logger.info("Received: "+product);
		   
	   }

	  
}






















