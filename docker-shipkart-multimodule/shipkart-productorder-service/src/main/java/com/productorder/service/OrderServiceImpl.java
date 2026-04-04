package com.productorder.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productorder.model.Orders;
import com.productorder.model.OrdersDto;
import com.productorder.repository.IOrderRepository;
import com.shared.models.OrderCreatedEvent;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private IOrderRepository repository;
	@Autowired
	private KafkaTemplate<String, OrderCreatedEvent> template;
	@Value(value = "${topicName}")
	private String topicName;
	
	@Override
	@Transactional
	public String createOrder(OrdersDto ordersDto) {
		//convert orderdto to orders
		Orders order = mapper.map(ordersDto, Orders.class);
		//save the order to the database
		Orders savedOrder=  repository.save(order);
		// get the orderid and totalPrice
		int orderId = savedOrder.getOrderId();
		double totalPrice = savedOrder.getTotalPrice();
		String status = savedOrder.getStatus();
		// create an event - OrderCreatedEvent
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(orderId,totalPrice, status);
		//send to Kafka
		template.send(topicName, savedOrder.getOrderId().toString(),orderCreatedEvent);
		return "order placed succesfully";
	}

	@Override
	public void updateOrder(int orderId, String paymentStatus) {
		//find order by id
		Orders orders  = repository.findById(orderId)
				                  .orElseThrow(()-> new RuntimeException("invalid orderid"));
		// paymentStatus
		if(paymentStatus.equals("SUCCESS"))
			//check in inventory if stock available
			orders.setStatus("PLACED");
		else
			orders.setStatus("CANCELLED");
		//call save method. whole object will be saveD
		repository.save(orders);
		
			
			
	}

}
