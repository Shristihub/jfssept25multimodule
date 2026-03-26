package com.productorder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productorder.model.OrdersDto;
import com.productorder.service.IOrderService;

@RestController
@RequestMapping("/order-service/v1")
public class OrderController {
  
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/place-order")
	String placeOrder(@RequestBody OrdersDto orderDto) {
		orderService.createOrder(orderDto);
		return "order placed";
	}
}
