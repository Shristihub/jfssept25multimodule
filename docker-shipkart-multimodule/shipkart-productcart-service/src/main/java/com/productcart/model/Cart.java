package com.productcart.model;

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
@Entity
@ToString
public class Cart {

	@Id
	@GeneratedValue(generator = "cart_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cart_gen",sequenceName = "cart_seq",initialValue = 1,allocationSize = 1)
	private Integer cartId;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="cart_id")
	private List<CartItem> cartItems;
	private int userId;
    private double totalPrice;
    


	
}




