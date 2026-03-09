package com.productcart.model;

import java.util.List;

import jakarta.persistence.Entity;
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
public class CartDto {

	private Integer cartId;
	private List<CartItemDto> cartItems;
	private int userId;
    private double totalPrice;
    


	
}




