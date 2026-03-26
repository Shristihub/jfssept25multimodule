package com.productpayment.service;

import com.productpayment.model.Payment;

public interface IPaymentService {
	
	boolean createPayment(Payment payment,boolean status);
	

}
