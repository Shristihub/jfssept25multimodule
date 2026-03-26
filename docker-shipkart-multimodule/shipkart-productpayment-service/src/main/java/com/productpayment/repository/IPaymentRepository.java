package com.productpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productpayment.model.Payment;

public interface IPaymentRepository extends JpaRepository<Payment, Integer>{

	
}
