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
public class Offers {
	@Id
	@GeneratedValue(generator = "offers_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "offers_gen",sequenceName = "offers_seq",initialValue = 50,allocationSize = 1)
	private Integer offersId;
	@Column(length = 20)
	private String offerName; //CASH BACK,NO COST EMI,BANK OFFER,PARTNER OFFER
	@Column(length = 30)
	private String description;
	
	
	
	
}