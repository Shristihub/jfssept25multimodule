package com.productcatalog.model.entities;

import jakarta.persistence.Column;
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
public class Features {
	@Id
	@GeneratedValue(generator = "features_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "features_gen",sequenceName = "features_seq",initialValue = 50,allocationSize = 1)
	private Integer featuresId;
	@Column(length = 30)
	private String description;
	@Column(length = 10)
	private String material;
	@Column(length = 10)
	private String color;
	
	
}



