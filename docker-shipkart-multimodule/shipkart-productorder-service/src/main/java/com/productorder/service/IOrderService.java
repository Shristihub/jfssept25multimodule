package com.productorder.service;

import com.productorder.model.OrdersDto;

public interface IOrderService {
	
	String createOrder(OrdersDto ordersDto);
	void updateOrder(int orderId, String status);
	
}
