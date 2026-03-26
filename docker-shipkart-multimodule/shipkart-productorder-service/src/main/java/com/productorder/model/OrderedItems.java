package com.productorder.model;

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
public class OrderedItems {
	private String productName;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_orders_seq")
    @SequenceGenerator(name = "prod_orders_seq", sequenceName = "prod_orders_seq", allocationSize = 1)
    private Integer orderedItemsId;  
	private Integer productId;
	private double price;
	private int quantity;
}
