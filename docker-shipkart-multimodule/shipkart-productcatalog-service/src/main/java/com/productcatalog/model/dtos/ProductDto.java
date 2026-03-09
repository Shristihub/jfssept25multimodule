package com.productcatalog.model.dtos;

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
public class ProductDto {
	
	private String productName;
	private Integer productId;
	private double price;
	private double ratings;
	private FeaturesDto features;
	private List<OffersDto> offers;
	private List<CategoryDto> categories;
	private BrandDto brand;
	private List<String> deliveryTypes;
	private List<String> paymentModes;
	
	private int stock;
}












