package com.productinventory.model;

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
@Entity
@ToString
public class Inventory {
	private int productId;
	@Id
	@GeneratedValue(generator = "inventory_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "inventory_gen",sequenceName = "inventory_seq",initialValue = 1,allocationSize = 1)
	private Integer inventoryId;
	private int stock;

}
