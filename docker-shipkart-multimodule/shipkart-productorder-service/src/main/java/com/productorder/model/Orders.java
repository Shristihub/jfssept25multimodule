package com.productorder.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Orders {
	@Id
	@GeneratedValue(generator = "orders_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "orders_gen",sequenceName = "orders_seq",initialValue = 1,allocationSize = 1)
	private Integer orderId;
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="orders_id")
	private List<OrderedItems> products;
	private double totalPrice;
	private String status; // CREATED, CANCELLED
}


