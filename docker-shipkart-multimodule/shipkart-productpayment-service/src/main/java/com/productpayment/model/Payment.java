package com.productpayment.model;

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
public class Payment {
	@Id
	@GeneratedValue(generator = "payment_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "payment_gen",sequenceName = "payment_seq",initialValue = 1,allocationSize = 1)
	private Integer paymentId;
	private int orderId;
	private double totalAmount;
	private String paymentStatus; // SUCCESS,FAILED,REFUNDED

}
