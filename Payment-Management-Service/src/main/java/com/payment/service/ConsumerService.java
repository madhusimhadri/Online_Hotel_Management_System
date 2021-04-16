package com.payment.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.config.AMQPConfiguration;
import com.payment.model.Invoice;

@Service
public class ConsumerService {
	
	@Autowired
	PaymentRequestService paymentReqService;
	
	@RabbitListener(queues = AMQPConfiguration.QUEUE)
	public void notify(Invoice invoice)
	{
		paymentReqService.rabbitQueueConsumer(invoice);
	}
}
