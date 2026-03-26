package com.productorder.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDto {

	private Integer orderId;
	private List<OrderedItems> products;
	private double totalPrice;
	private String status; // CREATED, CANCELLED
}
