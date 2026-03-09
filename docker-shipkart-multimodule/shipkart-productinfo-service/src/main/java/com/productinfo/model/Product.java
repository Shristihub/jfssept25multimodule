package com.productinfo.model;

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
public class Product implements Type{
	
	private String productName;
	private Integer productId;
	private double price;
	private double ratings;
	
	private Features features;
	private List<Offers> offers;
	private Brand brand;
	private List<Category> categories;
	private List<String> deliveryTypes; //PRIME,STANDARD, AMAZON
	private List<String> paymentModes; //CARD,COD,UPI
	
}












