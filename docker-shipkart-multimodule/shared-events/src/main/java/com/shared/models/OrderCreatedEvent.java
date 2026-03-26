package com.shared.models;

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
public class OrderCreatedEvent {
	private Integer orderId;
	private double totalPrice;
	private String status; // CREATED, CANCELLED

}
