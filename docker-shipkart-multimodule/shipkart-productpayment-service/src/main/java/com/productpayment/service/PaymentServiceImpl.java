package com.productpayment.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.productpayment.model.Payment;
import com.productpayment.repository.IPaymentRepository;
import com.shared.models.PaymentFailedEvent;
import com.shared.models.PaymentSucceededEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private final IPaymentRepository repository;
	private final KafkaTemplate<String, PaymentSucceededEvent> successTemplate;
	private final KafkaTemplate<String, PaymentFailedEvent> failedTemplate;

	@Override
	public boolean createPayment(Payment payment, boolean status) {
		Payment savedPayment = repository.save(payment); // save the payment
		// get the paymentId
//		int paymentId = savedPayment.getOrderId();
		if (status) {
			// create PaymentSucceededEvent
			PaymentSucceededEvent successEvent = new PaymentSucceededEvent(savedPayment.getOrderId(), "SUCCESS");
			// send the event to kafka server
			// behave like producer
			successTemplate.send("payment.success", String.valueOf(savedPayment.getOrderId()), successEvent);
			System.out.println(successEvent);
		} else {
			// create PaymentFailedEvent
			PaymentFailedEvent failedEvent = new PaymentFailedEvent(savedPayment.getOrderId(), "FAILED");
			System.out.println(failedEvent);
			// send the event to kafka server
			// behave like producer
			failedTemplate.send("payment.failed", String.valueOf(savedPayment.getOrderId()), failedEvent);
			
		}
		return status;
	}

}
