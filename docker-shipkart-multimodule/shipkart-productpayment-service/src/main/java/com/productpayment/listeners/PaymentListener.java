package com.productpayment.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.productpayment.model.Payment;
import com.productpayment.model.PaymentStatus;
import com.productpayment.service.IPaymentService;
import com.shared.models.OrderCreatedEvent;

@Component
public class PaymentListener {
	
	@Autowired
	private IPaymentService paymentService;
	Logger logger = LoggerFactory.getLogger(PaymentListener.class);
	
	@KafkaListener(topics = {"orders.created"},groupId = "order_group_id")
	public void handleCreateOrder(OrderCreatedEvent event) {
		//consume the event
		System.out.println(event);
		logger.info("....."+event+".....");
		//create a payment object
		Payment payment = new Payment(null,event.getOrderId(),event.getTotalPrice(),null);
		//do payment - mimic external payment call payAmount
		boolean status = processPayment(event.getTotalPrice());
		// if successful
		if(status)
			//add the success status to payment
		  payment.setPaymentStatus(PaymentStatus.SUCCESS.name());
		else
			//add the failed status
		 payment.setPaymentStatus(PaymentStatus.FAILED.name());
		
		//call PaymentService to save the payment
		paymentService.createPayment(payment,status);
		
	}
	
	// external api - razor api
		private boolean processPayment(double amount) {
			//call external app to do payment
			// write some functonality to do payment
			// we dont have any api to do payment
			return Math.random()>0.2; //80% returns true
		}
			
	

}
