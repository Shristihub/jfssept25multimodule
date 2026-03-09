package com.productcatalog.model.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = "products")
public class Brand {
	@Column(length = 30)
	private String brandName;
	@Id
	@GeneratedValue(generator = "brand_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "brand_gen",sequenceName = "brand_seq",initialValue = 20,allocationSize = 1)
	private Integer brandId;

	@OneToMany(mappedBy = "brand")
	private List<Product> products;
}










