package com.productcatalog.model.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@ToString(exclude = "products")
public class Category {
	@Column(length = 30)
	private String categoryName;
	@Id
	@GeneratedValue(generator = "category_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "category_gen",sequenceName = "category_seq",initialValue = 150,allocationSize = 1)
	private Integer categoryId;
	@ManyToMany(mappedBy = "categories")
	private List<Product> products;
	
}










