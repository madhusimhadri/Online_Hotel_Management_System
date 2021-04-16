package com.hotel.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.config.AMQPConfiguration;
import com.hotel.model.Invoice;
import com.hotel.model.Show;

@Service
public class InvoiceService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public Show displayInvoice(Invoice invoice)
	{
		rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE, AMQPConfiguration.ROUTING_KEY, invoice);
		return new Show("Sending Invoice to Queue - ", "Successfull");
	}
}
