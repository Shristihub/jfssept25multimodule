package com.productcart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(generator = "cartitem_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cartitem_gen",sequenceName = "cartitem_seq",initialValue = 1,allocationSize = 1)
	private Integer cartItemId;
	private String productName;
	private Integer productId;
	private double price;
	private int quantity;

	
	
	

}
