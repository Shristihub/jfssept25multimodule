package com.productorder.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.productorder.service.IOrderService;
import com.shared.models.PaymentFailedEvent;
import com.shared.models.PaymentSucceededEvent;

@Component
public class OrderListener {

	@Autowired
	private IOrderService orderService;
	
	@KafkaListener(topics = { "payment.success"},groupId = "order-service",containerFactory = "successContainerFactory" )
	public void handlePaymentSuccess(PaymentSucceededEvent event){
		System.out.println(event);
		String status = event.getStatus();
		int orderId = event.getOrderId();
		//update the order 
		orderService.updateOrder(orderId,status);
		
	}
	@KafkaListener(topics = { "payment.failed"},groupId = "order-service",containerFactory = "failedContainerFactory" )
	public void handlePaymentFailed(PaymentFailedEvent event){
		System.out.println(event);
		String status = event.getStatus();
		int orderId = event.getOrderId();
		//update the order
		orderService.updateOrder(orderId,status);
		
	}


}
