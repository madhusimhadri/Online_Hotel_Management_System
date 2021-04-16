package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.StripeClient;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private StripeClient stripeClient;
	
	@Autowired
	PaymentController(StripeClient stripeClient)
	{
		this.stripeClient = stripeClient;
	}
	
	@PostMapping("/createCustomer")
	public Customer createCustomer(@RequestParam String token, @RequestParam String email, @RequestParam String invoiceId) throws Exception
	{
		return stripeClient.createCustomer(token, email, invoiceId);
	}
	
	@PostMapping("/chargeCustomer")
	public Charge chargeCreditCard(@RequestParam String invoiceId, @RequestParam int amount) throws Exception
	{
		return stripeClient.chargeCreditCard(invoiceId, amount);
	}
}
